package com.hresource.app.server.repository.appinsight.alarms;
import com.hresource.app.shared.appinsight.alarms.ArtLogConfig;

import com.hresource.app.shared.appinsight.alarms.ArtLogSeverity;

import java.util.List;

public interface ArtAlarmLoggerRepository {
	public void saveLoggerConfig(ArtLogConfig awsLogConfig);

	public void updateLoggerConfig(ArtLogConfig awsLogConfig);

	public void mergeSeverity(ArtLogSeverity radLogSeverity);

	public ArtLogSeverity getBySeverityId(int severityId);

	public void persistSeverity(ArtLogSeverity awsLogSeverity);

	public List<ArtLogConfig> findAll();

	public List<ArtLogSeverity> getBySeverity(Integer severity);
}
