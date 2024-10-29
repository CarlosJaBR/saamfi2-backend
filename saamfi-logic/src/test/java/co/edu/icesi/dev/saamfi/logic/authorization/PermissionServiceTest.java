package co.edu.icesi.dev.saamfi.logic.authorization;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.edu.icesi.dev.saamfi.dtos.speout.SaamfiPermissionsSpeOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiPermissionnStdOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiPermission;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.logic.implementation.authorization.SaamfiPermissionServiceImpl;
import co.edu.icesi.dev.saamfi.mappers.AuthorizationMapper;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiPermissionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRoleRepo;

public class PermissionServiceTest {

	@Mock
	private SaamfiRoleRepo roleeRepo;

	@InjectMocks
	private SaamfiPermissionServiceImpl permissionServiceImpl;

	@Mock
	private SaamfiPermissionRepo permissionRepo;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetExistingPermissionsAdmUni1() {

		ArrayList<SaamfiPermission> permissions = new ArrayList<SaamfiPermission>();

		SaamfiPermission p1 = new SaamfiPermission();
		p1.setPermId(100);

		SaamfiPermission p2 = new SaamfiPermission();
		p2.setPermId(101);

		permissions.add(p1);
		permissions.add(p2);

		List<SaamfiPermissionnStdOutDTO> permissionDTO = AuthorizationMapper.INSTANCE
				.asPermissionnStdOutDTO(permissions);
		SaamfiPermissionsSpeOutDTO permissionSpeDTO = new SaamfiPermissionsSpeOutDTO();
		permissionSpeDTO.setPermissions(permissionDTO);

		when(permissionRepo.findAll()).thenReturn(permissions);

		SaamfiPermission pCheck1 = permissions.get(0);
		SaamfiPermission pCheck2 = permissions.get(1);

		SaamfiPermissionsSpeOutDTO speDTO = permissionServiceImpl.getExistingPermissionsAdm();

		assertEquals(pCheck1.getPermId(), speDTO.getPermissions().get(0).getPermId());
		assertEquals(pCheck2.getPermId(), speDTO.getPermissions().get(1).getPermId());

	}

	@Test
	public void testGetExistingPermissionsOperUni2() {

		ArrayList<SaamfiPermission> permissions = new ArrayList<SaamfiPermission>();

		SaamfiPermission p1 = new SaamfiPermission();
		p1.setPermId(102);

		SaamfiPermission p2 = new SaamfiPermission();
		p2.setPermId(103);

		permissions.add(p1);
		permissions.add(p2);

		List<SaamfiPermissionnStdOutDTO> permissionDTO = AuthorizationMapper.INSTANCE
				.asPermissionnStdOutDTO(permissions);
		SaamfiPermissionsSpeOutDTO permissionSpeDTO = new SaamfiPermissionsSpeOutDTO();
		permissionSpeDTO.setPermissions(permissionDTO);

		when(permissionRepo.findPermissionByPermissiontype()).thenReturn(permissions);

		SaamfiPermission pCheck1 = permissions.get(0);
		SaamfiPermission pCheck2 = permissions.get(1);

		SaamfiPermissionsSpeOutDTO speDTO = permissionServiceImpl.getExistingPermissionsOper();

		assertEquals(pCheck1.getPermId(), speDTO.getPermissions().get(0).getPermId());
		assertEquals(pCheck2.getPermId(), speDTO.getPermissions().get(1).getPermId());

	}

	/**
	 * @Test testGetRolesAssociatedWithPermissionAdmUni1
	 * @Description: this test case test that an admin can get the roles associated
	 *               to a permission
	 *
	 *               The expected value is a list with three roles
	 */
	@Test
	@Disabled
	public void testGetRolesAssociatedWithPermissionAdmUni1() {

		long instId = 1;
		long permissionId = 1;

		long roleId1 = 1;
		long roleId2 = 2;
		long roleId3 = 3;

		List<SaamfiRole> roles = new ArrayList<SaamfiRole>();

		SaamfiRole r1 = new SaamfiRole();
		SaamfiRole r2 = new SaamfiRole();
		SaamfiRole r3 = new SaamfiRole();

		r1.setRoleId(roleId1);
		r2.setRoleId(roleId2);
		r3.setRoleId(roleId3);

		roles.add(r1);
		roles.add(r2);
		roles.add(r3);

		List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE.asRoleeStdOutDTO(roles);

		when(roleeRepo.getRolesByInstiAndPermissionId(instId, permissionId)).thenReturn(roles);

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionServiceImpl.getRolesAssociatedWithPermissionAdm(instId,
				permissionId);

		assertEquals(rolesDTO.get(0).getRoleId(), rolesDTO_returned.get(0).getRoleId());
		assertEquals(rolesDTO.get(1).getRoleId(), rolesDTO_returned.get(1).getRoleId());
		assertEquals(rolesDTO.get(2).getRoleId(), rolesDTO_returned.get(2).getRoleId());
	}

	/**
	 * @Test testGetRolesAssociatedWithPermissionAdmUni2
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that an admin cant get the roles associated
	 *               to a permission, if the permission not has been assigned to any
	 *               role
	 *
	 *               The expected value is null, because there are no roles
	 *               associated to the permission
	 */
	@Test
	public void testGetRolesAssociatedWithPermissionAdmUni2() {

		long instId = 1;
		long permissionId = 1;

		when(roleeRepo.getRolesByInstiAndPermissionId(instId, permissionId)).thenReturn(null);

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionServiceImpl.getRolesAssociatedWithPermissionAdm(instId,
				permissionId);

		assertNull(rolesDTO_returned);
	}

	/**
	 * @Test testGetRolesAssociatedWithPermissionOperUni3
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that an oper can get the roles associated
	 *               to a permission
	 *
	 *               The expected value is a list with three roles
	 */
	@Test
	public void testGetRolesAssociatedWithPermissionOperUni3() {

		long instId = 1;
		long permissionId = 1;

		long roleId1 = 1;
		long roleId2 = 2;
		long roleId3 = 3;

		List<SaamfiRole> roles = new ArrayList<SaamfiRole>();

		SaamfiRole r1 = new SaamfiRole();
		SaamfiRole r2 = new SaamfiRole();
		SaamfiRole r3 = new SaamfiRole();

		r1.setRoleId(roleId1);
		r2.setRoleId(roleId2);
		r3.setRoleId(roleId3);

		roles.add(r1);
		roles.add(r2);
		roles.add(r3);

		List<SaamfiRoleeStdOutDTO> rolesDTO = AuthorizationMapper.INSTANCE.asRoleeStdOutDTO(roles);

		when(roleeRepo.getRolesByPermissionIdOper(instId, permissionId)).thenReturn(roles);

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionServiceImpl.getRolesAssociatedWithPermissionOper(
				instId,
				permissionId);

		assertEquals(rolesDTO.get(0).getRoleId(), rolesDTO_returned.get(0).getRoleId());
		assertEquals(rolesDTO.get(1).getRoleId(), rolesDTO_returned.get(1).getRoleId());
		assertEquals(rolesDTO.get(2).getRoleId(), rolesDTO_returned.get(2).getRoleId());
	}

	/**
	 * @Test testGetRolesAssociatedWithPermissionOperUni4
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that an oper cant get the roles associated
	 *               to a permission, if the permission not has been assigned to any
	 *               role
	 *
	 *               The expected value is null, because there are no roles
	 *               associated to the permission
	 */
	@Test
	public void testGetRolesAssociatedWithPermissionOperUni4() {

		long instId = 1;
		long permissionId = 1;

		when(roleeRepo.getRolesByPermissionIdOper(instId, permissionId)).thenReturn(null);

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionServiceImpl.getRolesAssociatedWithPermissionOper(
				instId,
				permissionId);

		assertNull(rolesDTO_returned);
	}

}
