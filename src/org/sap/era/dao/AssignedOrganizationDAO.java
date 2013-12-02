package org.sap.era.dao;

import org.sap.era.persistence.AssignedOrgnazition;
import org.springframework.stereotype.Repository;

@Repository(value = "assignedOrganizationDAO")
public class AssignedOrganizationDAO extends BaseDAO<AssignedOrgnazition, Long> {
}
