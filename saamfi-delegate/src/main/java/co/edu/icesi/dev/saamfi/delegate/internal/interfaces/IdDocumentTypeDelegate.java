package co.edu.icesi.dev.saamfi.delegate.internal.interfaces;

import co.edu.icesi.dev.saamfi.dtos.stdout.IdDocumentTypeOutDTO;

public interface IdDocumentTypeDelegate {
	
	public Iterable<IdDocumentTypeOutDTO> getTdDocumentTypes(long instid);
	
}
