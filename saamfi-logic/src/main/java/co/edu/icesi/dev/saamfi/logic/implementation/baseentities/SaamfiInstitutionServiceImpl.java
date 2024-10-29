package co.edu.icesi.dev.saamfi.logic.implementation.baseentities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.dev.saamfi.dtos.speout.InstitutionSpecOutDTO;
import co.edu.icesi.dev.saamfi.dtos.stdin.SaamfiInstitutionStdInDTO;
import co.edu.icesi.dev.saamfi.entities.SaamfiInstitution;
import co.edu.icesi.dev.saamfi.entities.SaamfiParameter;
import co.edu.icesi.dev.saamfi.entities.SaamfiRole;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystem;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInst;
import co.edu.icesi.dev.saamfi.entities.SaamfiSystmInstPK;
import co.edu.icesi.dev.saamfi.logic.exceptions.BadRequestDataException;
import co.edu.icesi.dev.saamfi.logic.exceptions.NoResultException;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiInstitutionService;
import co.edu.icesi.dev.saamfi.logic.interfaces.baseentities.SaamfiParameterService;
import co.edu.icesi.dev.saamfi.mappers.InstitutionSpecOutMapperImpl;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiInstitutionRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiRoleRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiSystemRepo;
import co.edu.icesi.dev.saamfi.repository.interfaces.SaamfiSystmInstRepo;

@Service
@Transactional
public class SaamfiInstitutionServiceImpl implements SaamfiInstitutionService {

	@Autowired
	private SaamfiInstitutionRepo institutionRepo;
	@Autowired
	private SaamfiSystemRepo systemRepo;
	@Autowired
	private SaamfiSystmInstRepo systmInstRepo;
	@Autowired
	private SaamfiRoleRepo saamfiRoleRepo;
	@Autowired
	private SaamfiParameterService saamfiParameterService;

	@Override
	public List<InstitutionSpecOutDTO> getInstitutions() throws NoResultException {
		List<SaamfiInstitution> it = institutionRepo.findAll();
		if (it.isEmpty()) {
			throw new NoResultException();
		} else {
			List<InstitutionSpecOutDTO> institutions = InstitutionSpecOutMapperImpl.INSTANCE
					.asInstitutionsStdOutDTO(it);
			return institutions;
		}
	}

	@Override
	public InstitutionSpecOutDTO saveInstitution(SaamfiInstitutionStdInDTO institution)
			throws BadRequestDataException, NoResultException {
		if (institution.getInstName() == null || institution.getInstName().isEmpty()) {
			throw new BadRequestDataException();
		} else {
			SaamfiInstitution inst = institution.getParamsToSaamfiInstitution();
			SaamfiInstitution instSaved = institutionRepo.save(inst);

			if (institution.getParameters() != null) {
				for (SaamfiParameter param : institution.getParameters()) {
					saamfiParameterService.saveParameter(param, instSaved.getInstId());
				}
			}

			InstitutionSpecOutDTO instOut = InstitutionSpecOutMapperImpl.INSTANCE
					.saamfiInstitutionToInstitutionSpecOutDTO(instSaved);
			if (institution.getSystemsId() != null) {
				for (Long sysid : institution.getSystemsId()) {
					SaamfiSystmInst systmInst = new SaamfiSystmInst();
					Optional<SaamfiSystem> sysOp = systemRepo.findById(sysid);
					if (sysOp.isEmpty()) {
						throw new NoResultException();
					}
					SaamfiSystem sys = sysOp.get();

					SaamfiSystmInstPK pk = new SaamfiSystmInstPK();
					pk.setInstInstId(inst.getInstId());
					pk.setSystmSysId(sys.getSysId());
					systmInst.setId(pk);
					systmInst.setSaamfiInstitution(inst);
					systmInst.setSaamfiSystem(sys);
					systmInstRepo.save(systmInst);
				}
			}
			if (institution.getRolesId() != null) {
				List<SaamfiRole> roles = new ArrayList<SaamfiRole>();
				for (Long rolesid : institution.getRolesId()) {
					Optional<SaamfiRole> roleOp = saamfiRoleRepo.findById(rolesid);
					if (roleOp.isEmpty()) {
						throw new NoResultException();
					}
					SaamfiRole role = roleOp.get();
					roles.add(role);
				}
				inst.setSaamfiRoles(roles);
			}
			return instOut;
		}
	}

	@Override
	public InstitutionSpecOutDTO editInstitution(SaamfiInstitution institution, long instid)
			throws NoResultException, BadRequestDataException {
		if (institution == null || institution.getInstName() == null || institution.getInstName().isEmpty()) {
			throw new BadRequestDataException();
		}
		SaamfiInstitution inst = institutionRepo.findById(instid).get();
		if (inst != null) {
			inst.setInstId(instid);
			inst.setInstAcademicserverurl(
					institution.getInstAcademicserverurl() != null ? institution.getInstAcademicserverurl() : "");
			inst.setInstAcadloginpassword(
					institution.getInstAcadloginpassword() != null ? institution.getInstAcadloginpassword() : "");
			inst.setInstAcadloginurl(
					institution.getInstAcadloginurl() != null ? institution.getInstAcadloginurl() : "");
			inst.setInstAcadloginusername(
					institution.getInstAcadloginusername() != null ? institution.getInstAcadloginusername() : "");
			inst.setInstAcadpersoninfodocurl(
					institution.getInstAcadpersoninfodocurl() != null ? institution.getInstAcadpersoninfodocurl() : "");
			inst.setInstAcadpersoninfoidur(
					institution.getInstAcadpersoninfoidur() != null ? institution.getInstAcadpersoninfoidur() : "");
			inst.setInstLdapbasedn(institution.getInstLdapbasedn() != null ? institution.getInstLdapbasedn() : "");
			inst.setInstLdappassword(
					institution.getInstLdappassword() != null ? institution.getInstLdappassword() : "");
			inst.setInstLdapurl(institution.getInstLdapurl() != null ? institution.getInstLdapurl() : "");
			inst.setInstLdapusername(
					institution.getInstLdapusername() != null ? institution.getInstLdapusername() : "");
			inst.setInstLdapusersearchbase(
					institution.getInstLdapusersearchbase() != null ? institution.getInstLdapusersearchbase() : "");
			inst.setInstLdapusersearchfilter(
					institution.getInstLdapusersearchfilter() != null ? institution.getInstLdapusersearchfilter() : "");
			inst.setInstName(institution.getInstName());
			inst.setSaamfiParameters(
					institution.getSaamfiParameters() != null ? institution.getSaamfiParameters() : new ArrayList<>());
			inst.setSaamfiRoles(
					institution.getSaamfiRoles() != null ? institution.getSaamfiRoles() : new ArrayList<>());

			SaamfiInstitution instSaved = institutionRepo.save(inst);
			InstitutionSpecOutDTO instOut = InstitutionSpecOutMapperImpl.INSTANCE
					.saamfiInstitutionToInstitutionSpecOutDTO(instSaved);
			return instOut;
		} else {
			throw new NoResultException();
		}
	}

	@Override
	public InstitutionSpecOutDTO deleteInstitution(long idinstitution)
			throws NoResultException, BadRequestDataException {
		if (idinstitution < 0) {
			throw new BadRequestDataException();
		} else {
			Optional<SaamfiInstitution> inst = institutionRepo.findById(idinstitution);
			if (!inst.isEmpty()) {
				InstitutionSpecOutDTO institution = InstitutionSpecOutMapperImpl.INSTANCE
						.saamfiInstitutionToInstitutionSpecOutDTO(inst.get());
				institutionRepo.delete(inst.get());
				return institution;
			} else {
				throw new NoResultException();
			}
		}
	}

	@Override
	public InstitutionSpecOutDTO getInstitutionById(long idinstitution)
			throws NoResultException, BadRequestDataException {
		if (idinstitution < 0) {
			throw new BadRequestDataException();
		} else {
			Optional<SaamfiInstitution> inst = institutionRepo.findById(idinstitution);
			if (!inst.isEmpty()) {
				InstitutionSpecOutDTO institution = InstitutionSpecOutMapperImpl.INSTANCE
						.saamfiInstitutionToInstitutionSpecOutDTO(inst.get());
				return institution;
			} else {
				throw new NoResultException();
			}
		}

	}

	@Override
	public InstitutionSpecOutDTO getInstitutionBySystemId(long sysid, long instid) throws Exception {
		Optional<SaamfiSystem> sysOp = systemRepo.findById(sysid);
		Optional<SaamfiInstitution> instOp = institutionRepo.findById(instid);
		if (instOp.isEmpty())
			throw new NoResultException();
		if (sysOp.isEmpty())
			throw new NoResultException();

		SaamfiSystem sys = sysOp.get();
		SaamfiInstitution inst = instOp.get();

		Optional<SaamfiSystmInst> sysInstOp = systmInstRepo.findBySaamfiSystemAndSaamfiInstitution(sys, inst);
		if (sysInstOp.isEmpty())
			throw new Exception("Esta institución no pertenece a este sistema");

		InstitutionSpecOutDTO institution = InstitutionSpecOutMapperImpl.INSTANCE
				.saamfiInstitutionToInstitutionSpecOutDTO(inst);
		return institution;
	}

	@Override
	public SaamfiInstitution findById(long id) {
		return institutionRepo.findById(id).get();
	}

	@Override
	public List<InstitutionSpecOutDTO> findInstitutionsByAdminInstitutions(Long userId) throws NoResultException {
		List<SaamfiInstitution> it = institutionRepo.findInstitutionsByAdminInstitutions(userId);
		System.out.println(it.size());
		if (it.isEmpty()) {
			throw new NoResultException();
		} else {
			List<InstitutionSpecOutDTO> institutions = InstitutionSpecOutMapperImpl.INSTANCE
					.asInstitutionsStdOutDTO(it);
			return institutions;
		}
	}
}
