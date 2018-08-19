package br.com.luciano.npj.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luciano.npj.service.exception.NegocioException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {
	
	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorio(Integer id, Map<String, Object> parametros, String caminhoArquivo) throws Exception {
		Connection connection = null;
		
		InputStream inputStream = this.getClass().getResourceAsStream(caminhoArquivo);
		
		try {
			connection = this.dataSource.getConnection();
			JasperPrint jasper = JasperFillManager.fillReport(inputStream, parametros, connection);
	
			return JasperExportManager.exportReportToPdf(jasper);
		} catch (Exception e) {
			throw new NegocioException("Erro gerando relatório" + e);
		} finally {		
			connection.close();
		}
		
	}

}
