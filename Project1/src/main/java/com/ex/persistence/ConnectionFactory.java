package com.ex.persistence;

import java.sql.Connection;

public interface ConnectionFactory
{
    Connection newConnection();
    void destroy();
}
