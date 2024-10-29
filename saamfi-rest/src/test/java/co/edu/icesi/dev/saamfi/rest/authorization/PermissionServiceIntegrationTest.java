package co.edu.icesi.dev.saamfi.rest.authorization;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.saamfi.dtos.stdout.SaamfiRoleeStdOutDTO;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiPermissionService;

@SpringBootTest
@Transactional
public class PermissionServiceIntegrationTest {

	@Autowired
	private SaamfiPermissionService permissionService;

	/**
	 * @Test testGetRolesAssociatedWithPermissionAdmInt1
	 *
	 * @author Christian Lopez
	 *
	 * @description: this test case test that an admin can get the roles associated
	 *               with a permission
	 *
	 *               The expected is a list with the roles linked to a permission
	 */
	@Test
	public void testGetRolesAssociatedWithPermissionAdmInt1() {
		long instId = 1;
		long permissionId = 1;

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionService.getRolesAssociatedWithPermissionAdm(instId,
				permissionId);
		assertNotNull(rolesDTO_returned);
	}

	/**
	 * @Test: testGetRolesAssociatedWithPermissionAdmInt2
	 *
	 * @author Christian Lopez
	 *
	 * @description: this test case test that an admin cant get the roles associated
	 *               with a permission, if the permission has not been assigned to
	 *               any role
	 *
	 *               The expected value is null, because there are no roles with the
	 *               indicated permission
	 */
	@Test
	public void testGetRolesAssociatedWithPermissionAdmInt2() {
		long instId = 1;
		long permissionId = 104;

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionService.getRolesAssociatedWithPermissionAdm(instId,
				permissionId);
		assertTrue(rolesDTO_returned.size() == 0);
	}

	/**
	 * @Test testGetRolesAssociatedWithPermissionOperInt3
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that an oper can get the roles associated
	 *               with a permission
	 *
	 *               The expected is a list with the roles linked to a permission
	 */
	@Test
	public void testGetRolesAssociatedWithPermissionOperInt3() {
		long instId = 1;
		long permissionId = 1;

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionService.getRolesAssociatedWithPermissionOper(instId,
				permissionId);
		assertNotNull(rolesDTO_returned);
	}

	/**
	 * @Test testGetRolesAssociatedWithPermissionOperInt4
	 *
	 * @author Christian Lopez
	 *
	 * @Description: this test case test that an oper cant get the roles associated
	 *               with a permission, if the permission has not been assigned to
	 *               any role
	 *
	 *               The expected value is null, because there are no roles with the
	 *               indicated permission
	 */
	@Test
	public void testGetRolesAssociatedWithPermissionOperInt4() {
		long instId = 1;
		long permissionId = 104;

		List<SaamfiRoleeStdOutDTO> rolesDTO_returned = permissionService.getRolesAssociatedWithPermissionOper(instId,
				permissionId);
		assertTrue(rolesDTO_returned.size() == 0);
	}
}
