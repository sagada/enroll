package com.sugang.toys.command.config.db;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Profile("dev")
public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey()
    {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? "slave"
                : "master";
    }
}
