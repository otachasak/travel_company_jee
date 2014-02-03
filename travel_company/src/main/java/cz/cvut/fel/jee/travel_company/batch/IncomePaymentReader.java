package cz.cvut.fel.jee.travel_company.batch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class IncomePaymentReader extends AbstractItemReader{
	
	@Inject
	private JobContext jobContext;
	private BufferedReader br;
	private Integer recordNumber = 0;

	@Override
	public Object readItem() throws Exception {
		Object record = null;
		String line = br.readLine();
	       if (line != null) {
	            String[] fields = line.split("[, \t\r\n]+");
	            PaymentIncomeRecord payment = new PaymentIncomeRecord(Long.parseLong(fields[0]), new BigDecimal(fields[1]));
	            record = payment;
	            recordNumber++;
	        }
	        return record;
	}

	@Override
	public void open(Serializable checkpoint) throws Exception {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
     //   Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
     //   String resourceName = (String) jobParameters.get("inputDataFileName");
        FileInputStream inputStream = new FileInputStream(this.getClass().getClassLoader().getResource("/META-INF/payments.txt").getFile());        
        br = new BufferedReader(new InputStreamReader(inputStream));

        if (checkpoint != null)
            recordNumber = (Integer) checkpoint;
        for (int i=1; i<recordNumber; i++) {
            br.readLine();
        }
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return recordNumber;
	}

}
