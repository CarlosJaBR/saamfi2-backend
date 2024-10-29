package co.edu.icesi.dev.saamfi.logic.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermissionType;
import co.edu.icesi.dev.saamfi.entities.SaamfiRolePerm;
import co.edu.icesi.dev.saamfi.entities.SaamfiRolePermPK;
import co.edu.icesi.dev.saamfi.logic.implementation.authorization.SaamfiRolePermServiceImpl;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiPermissionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRolePermRepo;

public class RolePermissionServiceTest {

	@Mock
	private SaamfiRolePermRepo rolePermissionRepo;

	@Mock
	private SaamfiPermissionRepo permissionRepo;

	@InjectMocks
	private SaamfiRolePermServiceImpl rolePermissionService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @Test testAddRolePermissionAdmUni1
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that a admin can add a permission to a role
	 *
	 *               The expected value is true, because the the permission is not
	 *               linked with the role
	 */
	@Test
	public void testAddRolePermissionAdmUni1() {

		int roleId = 1;
		int permissionId = 1;

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setRoleRoleId(roleId);
		rolePermissionPK.setPermPermId(permissionId);
		SaamfiRolePerm rolePermissionToAdd = new SaamfiRolePerm();
		rolePermissionToAdd.setId(rolePermissionPK);

		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleId, permissionId)).thenReturn(null);
		when(rolePermissionRepo.save(rolePermissionToAdd)).thenReturn(rolePermissionToAdd);

		boolean stateRolePermissionRecover = rolePermissionService.addRolePermissionAdm(roleId, permissionId);
		assertTrue(stateRolePermissionRecover);
	}

	/**
	 * @Test testAddRolePermissionAdmUni2
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that a admin cant add a permission to a
	 *               role, if the permission is linked with that role
	 *
	 *               The expected value is false, because the the permission is
	 *               linked with the role
	 */
	@Test
	public void testAddRolePermissionAdmUni2() {

		int roleId = 1;
		int permissionId = 1;

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setRoleRoleId(roleId);
		rolePermissionPK.setPermPermId(permissionId);
		SaamfiRolePerm rolePermissionToAdd = new SaamfiRolePerm();
		rolePermissionToAdd.setId(rolePermissionPK);

		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleId, permissionId))
				.thenReturn(rolePermissionToAdd);
		when(rolePermissionRepo.save(rolePermissionToAdd)).thenReturn(rolePermissionToAdd);
		boolean stateRolePermissionRecover = rolePermissionService.addRolePermissionAdm(roleId, permissionId);
		assertFalse(stateRolePermissionRecover);
	}

	/**
	 * @Test testAddRolePermissionOperUni3
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that a oper can add a permission to a role,
	 *               if the permission is not typePermission 1 and the permission is
	 *               not linked
	 *
	 *               The expected value is true, because the the permission is not
	 *               linked with the role and the permission is not typePermission 1
	 */
	@Test
	@Disabled
	public void testAddRolePermissionOperUni3() {

		int roleId = 228;
		long permissionId = 98;

		SaamfiPermission permission = new SaamfiPermission();
		SaamfiPermissionType permissionType = new SaamfiPermissionType();

		permissionType.setPermtypeId(2);
		permission.setSaamfiPermissionType(permissionType);

		Optional<SaamfiPermission> permissionOpt = Optional.of(permission);

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setRoleRoleId(roleId);
		rolePermissionPK.setPermPermId(permissionId);
		SaamfiRolePerm rolePermissionToAdd = new SaamfiRolePerm();
		rolePermissionToAdd.setId(rolePermissionPK);

		when(permissionRepo.findById(permissionId)).thenReturn(permissionOpt);
		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleId, permissionId)).thenReturn(null);
		when(rolePermissionRepo.save(rolePermissionToAdd)).thenReturn(rolePermissionToAdd);
		boolean stateRolePermissionRecover = rolePermissionService.addRolePermissionOper(roleId, permissionId);
		assertTrue(stateRolePermissionRecover);
	}

	/**
	 * @Test testAddRolePermissionOperUni4
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that a oper cant add a permission to a
	 *               role, if the permission is not typePermission 1 and is linked
	 *               with a role
	 *
	 *               The expected value is false, because the the permission is
	 *               linked with the role and the permission is not typePermission 1
	 */
	@Test
	@Disabled
	public void testAddRolePermissionOperUni4() {

		int roleId = 228;
		long permissionId = 98;

		SaamfiPermission permission = new SaamfiPermission();
		SaamfiPermissionType permissionType = new SaamfiPermissionType();

		permissionType.setPermtypeId(2);
		permission.setSaamfiPermissionType(permissionType);

		Optional<SaamfiPermission> permissionOpt = Optional.of(permission);

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setRoleRoleId(roleId);
		rolePermissionPK.setPermPermId(permissionId);
		SaamfiRolePerm rolePermissionToAdd = new SaamfiRolePerm();
		rolePermissionToAdd.setId(rolePermissionPK);

		when(permissionRepo.findById(permissionId)).thenReturn(permissionOpt);
		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(roleId, permissionId))
				.thenReturn(rolePermissionToAdd);
		when(rolePermissionRepo.save(rolePermissionToAdd)).thenReturn(rolePermissionToAdd);
		boolean stateRolePermissionRecover = rolePermissionService.addRolePermissionOper(roleId, permissionId);
		assertFalse(stateRolePermissionRecover);

	}

	/**
	 * @Test testDeletePermissionOfRoleAdm
	 * @description: Allows to re move permissions from a role other than Permission
	 *               1 for the adm
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	void testDeletePermissionOfRoleAdm() {
		int rol = 228;
		long perm = 99;

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setPermPermId(perm);
		rolePermissionPK.setRoleRoleId(rol);
		SaamfiRolePerm rolePermission = new SaamfiRolePerm();
		rolePermission.setId(rolePermissionPK);

		SaamfiPermission permissionn = new SaamfiPermission();
		SaamfiPermissionType permissiontype = new SaamfiPermissionType();

		permissiontype.setPermtypeId(2);
		permissionn.setSaamfiPermissionType(permissiontype);

		Optional<SaamfiPermission> permissionOpt = Optional.of(permissionn);

		when(permissionRepo.findById(perm)).thenReturn(permissionOpt);
		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(rol, perm)).thenReturn(rolePermission);
		when(rolePermissionRepo.save(rolePermission)).thenReturn(rolePermission);

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionAdm(rol, perm);
		assertTrue(stateDeletePermission);

	}

	/**
	 * @Test testDeletePermissionOfRoleAdm
	 * @description: Allows to re move permissions from a role with Permission 1 for
	 *               the adm
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	void testDeletePermissionOfRoleAdmin1() {
		int rol = 1;
		long perm = 1;

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setPermPermId(perm);
		rolePermissionPK.setRoleRoleId(rol);
		SaamfiRolePerm rolePermission = new SaamfiRolePerm();
		rolePermission.setId(rolePermissionPK);

		SaamfiPermission permissionn = new SaamfiPermission();
		SaamfiPermissionType permissiontype = new SaamfiPermissionType();

		permissiontype.setPermtypeId(1);
		permissionn.setSaamfiPermissionType(permissiontype);

		Optional<SaamfiPermission> permissionOpt = Optional.of(permissionn);

		when(permissionRepo.findById(perm)).thenReturn(permissionOpt);
		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(rol, perm)).thenReturn(rolePermission);
		when(rolePermissionRepo.save(rolePermission)).thenReturn(rolePermission);

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionAdm(rol, perm);
		assertTrue(stateDeletePermission);
	}

	/**
	 * @Test testDeletePermissionOfRoleAndOper
	 * @description: Allows to re move permissions from a role other than Permission
	 *               1 for the oper
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	void testDeletePermissionOfRoleAndOper() {
		int rol = 228;
		long perm = 99;

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setPermPermId(perm);
		rolePermissionPK.setRoleRoleId(rol);
		SaamfiRolePerm rolePermission = new SaamfiRolePerm();
		rolePermission.setId(rolePermissionPK);

		SaamfiPermission permissionn = new SaamfiPermission();
		SaamfiPermissionType permissiontype = new SaamfiPermissionType();

		permissiontype.setPermtypeId(2);
		permissionn.setSaamfiPermissionType(permissiontype);

		Optional<SaamfiPermission> permissionOpt = Optional.of(permissionn);

		when(permissionRepo.findById(perm)).thenReturn(permissionOpt);
		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(rol, perm)).thenReturn(rolePermission);
		when(rolePermissionRepo.save(rolePermission)).thenReturn(rolePermission);

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionAdm(rol, perm);
		assertTrue(stateDeletePermission);
	}

	/**
	 * @Test testDeletePermissionOfRoleOper1
	 * @description: No allows to re move permissions from a role with Permission 1
	 *               for the oper
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	@Disabled
	void testDeletePermissionOfRoleOper1() {
		int rol = 1;
		long perm = 1;

		SaamfiRolePermPK rolePermissionPK = new SaamfiRolePermPK();
		rolePermissionPK.setPermPermId(perm);
		rolePermissionPK.setRoleRoleId(rol);
		SaamfiRolePerm rolePermission = new SaamfiRolePerm();
		rolePermission.setId(rolePermissionPK);

		SaamfiPermission permissionn = new SaamfiPermission();
		SaamfiPermissionType permissiontype = new SaamfiPermissionType();

		permissiontype.setPermtypeId(1);
		permissionn.setSaamfiPermissionType(permissiontype);

		Optional<SaamfiPermission> permissionOpt = Optional.of(permissionn);

		when(permissionRepo.findById(perm)).thenReturn(permissionOpt);
		when(rolePermissionRepo.findByIdRoleRoleIdAndIdPermPermId(rol, perm)).thenReturn(rolePermission);
		when(rolePermissionRepo.save(rolePermission)).thenReturn(rolePermission);

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionOper(rol, perm);
		assertFalse(stateDeletePermission);
	}

	/**
	 * @Test testGetPermissionsOfARole_Uni1
	 * @Description: This test case test that a oper can't query the permissions of
	 *               a role
	 */
	@Test
	public void testGetPermissionsOfARoleUni1() {

		int roleId = 228;

		List<SaamfiPermission> permissions = new ArrayList<SaamfiPermission>();

		SaamfiPermission p1 = new SaamfiPermission();
		p1.setPermId(150);

		SaamfiPermission p2 = new SaamfiPermission();
		p2.setPermId(157);

		permissions.add(p1);
		permissions.add(p2);

		List<SaamfiPermissionnStdOutDTO> permissionsDTO = AuthorizationMapper.INSTANCE
				.asPermissionnStdOutDTO(permissions);

		when(permissionRepo.findPermissionByRoleId(roleId)).thenReturn(permissions);

		List<SaamfiPermissionnStdOutDTO> permissions2 = rolePermissionService.getPermissionsOfARole(roleId);
		assertEquals(permissionsDTO.get(0).getPermId(), permissions2.get(0).getPermId());

	}

}
