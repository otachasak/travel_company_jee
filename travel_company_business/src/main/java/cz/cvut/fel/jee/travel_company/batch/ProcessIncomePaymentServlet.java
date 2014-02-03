package cz.cvut.fel.jee.travel_company.batch;

import java.io.IOException;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessIncomePaymentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private static final String JOB_NAME = "IncomePaymentJob";

	@Override
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties props = new Properties();
        jobOperator.start(JOB_NAME, props);
	}

	
	
}
