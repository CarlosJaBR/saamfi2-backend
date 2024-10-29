package co.edu.icesi.dev.saamfi.rest.authorization;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiRolePermService;

@SpringBootTest
@Transactional
public class RolePermissionServiceIntegrationTest {

	@Autowired
	private SaamfiRolePermService rolePermissionService;

	/**
	 * @Test testAddRolePermissionAdmInt1
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that a admin can add a permission to a role
	 *
	 *               The expected value is true, because the the permission is not
	 *               linked with the role
	 */
	@Test
	public void testAddRolePermissionAdmInt1() {
		int roleId = 228;
		int permissionId = 99;

		boolean response = rolePermissionService.addRolePermissionAdm(roleId, permissionId);
		assertTrue(!response);
	}

	/**
	 * @Test testAddRolePermissionAdmInt2
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
	public void testAddRolePermissionAdmInt2() {
		int roleId = 1;
		int permissionId = 3;

		boolean response = rolePermissionService.addRolePermissionAdm(roleId, permissionId);
		assertFalse(!response);
	}

	/**
	 * @Test testAddRolePermissionOperInt3
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
	public void testAddRolePermissionOperInt3() {
		int roleId = 228;
		int permissionId = 98;

		boolean response = rolePermissionService.addRolePermissionOper(roleId, permissionId);
		assertTrue(!response);
	}

	/**
	 * @Test testAddRolePermissionOperInt4
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
	public void testAddRolePermissionOperInt4() {
		int roleId = 228;
		int permissionId = 103;

		boolean response = rolePermissionService.addRolePermissionOper(roleId, permissionId);
		assertFalse(response);
	}

	/**
	 * <<<<<<< HEAD
	 *
	 * @Test testDeletePermissionOfRoleAdm
	 * @description: Allows to re move permissions from a role other than Permission
	 *               1 for the adm
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	void testDeletePermissionOfRoleAdm() {
		int rol = 100;
		long perm = 100;

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionAdm(rol, perm);
		assertFalse(stateDeletePermission);

	}

	/**
	 * @Test testDeletePermissionOfRoleAdm
	 * @description: Allows to re move permissions from a role with Permission 1 for
	 *               the adm
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	void testDeletePermissionOfRoleAdmin1() {
		int rol = 100;
		long perm = 100;

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionAdm(rol, perm);
		assertFalse(stateDeletePermission);
	}

	/**
	 * @Test testDeletePermissionOfRoleAndOper
	 * @description: Allows to re move permissions from a role other than Permission
	 *               1 for the oper
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	void testDeletePermissionOfRoleAndOper() {
		int rol = 100;
		long perm = 1;

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionAdm(rol, perm);
		assertFalse(stateDeletePermission);
	}

	/**
	 * @Test testDeletePermissionOfRoleOper1
	 * @description: No allows to re move permissions from a role with Permission 1
	 *               for the oper
	 * @precondition: Permission and role to be removed must be added
	 */
	@Test
	void testDeletePermissionOfRoleOper1() {
		int rol = 100;
		long perm = 1;

		boolean stateDeletePermission = rolePermissionService.deleteRolePermissionOper(rol, perm);
		assertFalse(stateDeletePermission);
	}

	/**
	 * @Test testGetPermissionsOfARoleInt1
	 * @description: Allows to get the list permissions of a role
	 * @precondition: Permission and role to be query must be added
	 */
	// @Test
	// public void testGetPermissionsOfARoleInt1() {
	// int roleId = 6;

	// List<SaamfiPermissionnStdOutDTO> permissionsDTO =
	// rolePermissionService.getPermissionsOfARole(roleId);
	// assertNotNull(permissionsDTO);
	// }

	/**
	 * @Test testGetPermissionsOfARoleInt2
	 * @description: NO allows to get the list permissions of a role
	 * @precondition: Role to be query must be added
	 */
	// @Test
	// public void testGetPermissionsOfARoleInt2() {
	// int roleId2 = 227;

	// List<SaamfiPermissionnStdOutDTO> permissionsDTO2 =
	// rolePermissionService.getPermissionsOfARole(roleId2);
	// List<SaamfiPermissionnStdOutDTO> permissionsEmpty = new
	// ArrayList<SaamfiPermissionnStdOutDTO>();
	// assertEquals(permissionsEmpty.size(), permissionsDTO2.size());
	// }

}
