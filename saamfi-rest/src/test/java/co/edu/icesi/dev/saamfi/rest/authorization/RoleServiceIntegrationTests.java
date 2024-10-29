package co.edu.icesi.dev.saamfi.rest.authorization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiRoleeStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.logic.interfaces.authorization.SaamfiRoleService;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRoleRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class RoleServiceIntegrationTests {

	@Autowired
	private SaamfiRoleService roleService;

	@Autowired
	private SaamfiRoleRepo roleRepo;

	@Test
	@Rollback(true)
	public void deleteRoleSuccess() {

		SaamfiRole role = new SaamfiRole();
		role.setRoleId(99999);
		role.setRoleName("dummy role");
		role.setRoleDescription("dummy role");
		SaamfiRoleeStdInDTO roleDTO = new SaamfiRoleeStdInDTO();
		roleDTO.setRoleDescription(role.getRoleDescription());
		roleDTO.setRoleName(role.getRoleName());
		assertTrue(this.roleService.createRole(roleDTO, 1, 0));
		long roleId = this.roleRepo.findByRoleName("dummy role").getRoleId();
		assertEquals(1, this.roleService.deleteById(roleId));

	}

}
