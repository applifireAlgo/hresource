package com.hresource.app.server.repository.appinsight.alarms;
import com.hresource.app.shared.appinsight.alarms.ArtLogBoundedContext;
import java.util.List;


public interface ArtLogBoundedContextRepository {
	
    public void save(ArtLogBoundedContext artLogBoundedContext);
    
    public void update(ArtLogBoundedContext artLogBoundedContext);
    
    public ArtLogBoundedContext findByBoundedContextPrefix(String boundedContextPrefix);
	
	public List<ArtLogBoundedContext> findAll();
 
}
