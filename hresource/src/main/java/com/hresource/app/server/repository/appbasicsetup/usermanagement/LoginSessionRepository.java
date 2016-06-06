package com.hresource.app.server.repository.appbasicsetup.usermanagement;
import com.hresource.app.server.repository.core.SearchInterface;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.hresource.app.config.annotation.Complexity;
import com.hresource.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "Repository for LoginSession Transaction table", complexity = Complexity.MEDIUM)
public interface LoginSessionRepository<T> extends SearchInterface, LoginSessionRepositoryInterface {
}
