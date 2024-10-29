package co.edu.icesi.dev.saamfi.logic.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.logic.implementation.authorization.SaamfiRoleServiceImpl;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRolePermRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRoleRepo;

@SpringBootTest
@Disabled
public class RoleServiceTest {

	@InjectMocks
	SaamfiRoleServiceImpl roleServiceImpl;

	@Mock
	SaamfiRoleRepo roleeRepo;
	@Mock
	SaamfiRolePermRepo rolePermissionRepo;

	SaamfiRole rolee_01;
	SaamfiRole rolee_02;
	SaamfiRole rolee_03;
	SaamfiRole rolee_04;
	SaamfiRole rolee_05;

	public void getGlobalRole_1() {
		setup();
		List<SaamfiRole> roles = new ArrayList<SaamfiRole>();
		roles.add(rolee_01);
		roles.add(rolee_02);
		roles.add(rolee_03);
		when(roleeRepo.getRolesByPermTypeId(1, 1L)).thenReturn(roles);
		assertNotNull(roleServiceImpl.getRolesGlobal(1L));
	}

	@Test
	public void getGlobalRole_2() {
		setup();
		List<SaamfiRole> roles = new ArrayList<SaamfiRole>();
		roles.add(rolee_01);
		roles.add(rolee_02);
		roles.add(rolee_03);
		when(roleeRepo.getRolesByPermTypeId(1, 1L)).thenReturn(roles);
		assertEquals(roleServiceImpl.getRolesGlobal(1L).getRoles().size(), 3);

	}

	@Test
	public void getGlobalRole_3() {
		setup();
		List<SaamfiRole> roles = new ArrayList<SaamfiRole>();

		when(roleeRepo.getRolesByPermTypeId(1, 1L)).thenReturn(roles);
		assertEquals(roleServiceImpl.getRolesGlobal(1L).getRoles().size(), 0);

	}

	/**
	 * @Test saveRole1
	 * @Description When it is required to create a new role, the system sends a
	 *              discount with the corresponding information. If the name and
	 *              description are correct the method returns true and otherwise it
	 *              returns false
	 *
	 * @precondition
	 */
	@Test
	public void saveRole11() {
		setup();
		rolee_01.setRoleId(1);
		rolee_01.setRoleName("Jefe recursos humanos");
		rolee_01.setRoleDescription("Encargado recursos humanos");
		SaamfiInstitution inst = new SaamfiInstitution();
		inst.setInstId(1L);

		rolee_01.setSaamfiInstitution(inst);

		SaamfiRoleeStdInDTO roleeDto_01 = new SaamfiRoleeStdInDTO();
		roleeDto_01.setRoleName(rolee_01.getRoleName());
		roleeDto_01.setRoleDescription(rolee_01.getRoleDescription());

		roleeDto_01.setRoleDescription(roleeDto_01.getRoleDescription());
		when(roleeRepo.findByRoleName(rolee_01.getRoleName())).thenReturn(null);
		when(roleeRepo.save(rolee_01)).thenReturn(rolee_01);
		assertTrue(roleServiceImpl.createRole(roleeDto_01, 1, 0));
	}

	/**
	 * @Test saveRole2
	 * @Description When it is required to create a new role, the system sends a
	 *              discount with the corresponding information. If the name and
	 *              description are correct the method returns true and otherwise it
	 *              returns false
	 * @precondition
	 */
	@Test
	public void saveRole2() {

		setup();
		rolee_02.setRoleId(1);
		rolee_02.setRoleName("Jefe de siri comunicaciones");
		rolee_02.setRoleDescription("Encargado de todo el personal siri");

		SaamfiInstitution inst = new SaamfiInstitution();
		inst.setInstId(1L);

		rolee_02.setSaamfiInstitution(inst);

		SaamfiRoleeStdInDTO roleeDto_02 = new SaamfiRoleeStdInDTO();
		roleeDto_02.setRoleName(rolee_02.getRoleName());
		roleeDto_02.setRoleDescription(rolee_02.getRoleDescription());

		roleeDto_02.setRoleDescription(roleeDto_02.getRoleDescription());
		when(roleeRepo.findByRoleName(rolee_02.getRoleName())).thenReturn(rolee_02);
		when(roleeRepo.save(rolee_01)).thenReturn(rolee_01);
		assertTrue(!roleServiceImpl.createRole(roleeDto_02, 1, 0));
	}

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		rolee_01 = new SaamfiRole();
		rolee_02 = new SaamfiRole();
		rolee_03 = new SaamfiRole();
		rolee_04 = new SaamfiRole();
		rolee_05 = new SaamfiRole();
	}

	@Test
	public void testDeleteRole() {
		setup();
		rolee_01.setRoleId(999);
		rolee_01.setRoleName("dummy role");
		rolee_01.setRoleDescription("dummy role");
		SaamfiRoleeStdInDTO roleDTO = new SaamfiRoleeStdInDTO();
		roleDTO.setRoleDescription(rolee_01.getRoleDescription());
		roleDTO.setRoleName(rolee_01.getRoleName());
		this.roleServiceImpl.createRole(roleDTO, 1, 0);
		when(roleeRepo.removeByRoleId(999)).thenReturn((long) 1);
		assertEquals(roleServiceImpl.deleteById(999), 1);
	}

	/**
	 * @Test updateRole1
	 *
	 *       When it is required to update a new role, the system sends a discount
	 *       with the corresponding information. If the name and description are
	 *       correct the method returns true and otherwise it returns false
	 * @precondition
	 */

	@Test
	public void updateRole1() {

		setup();
		rolee_03.setRoleId(3);
		rolee_03.setRoleName("Jefe 1");
		rolee_03.setRoleDescription("Encargado de todo el personal 1");

		SaamfiInstitution inst = new SaamfiInstitution();
		inst.setInstId(1L);

		rolee_03.setSaamfiInstitution(inst);

		SaamfiRoleeStdInDTO roleeDto_test = new SaamfiRoleeStdInDTO();
		roleeDto_test.setRoleName("test name ");
		roleeDto_test.setRoleDescription(rolee_03.getRoleDescription());
		Optional<SaamfiRole> role = Optional.of(rolee_03);
		when(roleeRepo.findById(3L)).thenReturn(role);

		when(roleeRepo.findByRoleName(rolee_03.getRoleName())).thenReturn(rolee_03);
		when(roleeRepo.save(rolee_03)).thenReturn(rolee_03);
		assertTrue(roleServiceImpl.updateRole(roleeDto_test, 1, 0, 3L));

	}

	/**
	 * @Test updateRole2
	 *
	 * @Description When it is required to update a new role, the system sends a
	 *              discount with the corresponding information. If the name and
	 *              description are correct the method returns true and otherwise it
	 *              returns false
	 * @precondition
	 */
	@Test
	public void updateRole2() {
		setup();
		rolee_04.setRoleId(3);
		rolee_04.setRoleName("Jefe 2");
		rolee_04.setRoleDescription("Encargado de todo el personal 2");

		SaamfiInstitution inst = new SaamfiInstitution();
		inst.setInstId(1L);

		rolee_04.setSaamfiInstitution(inst);

		SaamfiRoleeStdInDTO roleeDto_test = new SaamfiRoleeStdInDTO();
		roleeDto_test.setRoleName("");
		roleeDto_test.setRoleDescription(rolee_04.getRoleDescription());
		Optional<SaamfiRole> role = Optional.of(rolee_04);
		when(roleeRepo.findById(4L)).thenReturn(role);
		when(roleeRepo.findByRoleName(rolee_04.getRoleName())).thenReturn(rolee_04);
		when(roleeRepo.save(rolee_04)).thenReturn(rolee_04);
		assertTrue(!roleServiceImpl.updateRole(roleeDto_test, 1, 0, 4L));

	}

	/**
	 * @Test updateRole
	 * @Description When it is required to update a new role, the system sends a
	 *              discount with the corresponding information. If the name and
	 *              description are correct the method returns true and otherwise it
	 *              returns false
	 *
	 * @precondition
	 */

	@Test
	public void updateRole3() {
		setup();
		rolee_05.setRoleId(4);
		rolee_05.setRoleName("Jefe 3 ");
		rolee_05.setRoleDescription("Encargado de todo el personal 3");

		SaamfiInstitution inst = new SaamfiInstitution();
		inst.setInstId(1L);

		rolee_05.setSaamfiInstitution(inst);

		SaamfiRoleeStdInDTO roleeDto_test = new SaamfiRoleeStdInDTO();
		roleeDto_test.setRoleName("test name ");
		roleeDto_test.setRoleDescription("");
		Optional<SaamfiRole> role = Optional.of(rolee_05);
		when(roleeRepo.findById(4L)).thenReturn(role);
		when(roleeRepo.findByRoleName(rolee_05.getRoleName())).thenReturn(rolee_05);
		when(roleeRepo.save(rolee_05)).thenReturn(rolee_05);
		assertTrue(!roleServiceImpl.updateRole(roleeDto_test, 1, 0, 5L));

	}
}
