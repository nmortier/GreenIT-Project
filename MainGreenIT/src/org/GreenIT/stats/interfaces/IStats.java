package org.GreenIT.stats.interfaces;

import java.sql.SQLException;

public interface IStats {

	public String getStats() throws SQLException;

	public void initialize() throws SQLException;
}
