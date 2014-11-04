package br.com.oobj.rest.jersey;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ConsumidorNotaFiscal {

	private static String WEB_SERVICE_OOBJ = "http://localhost:8080/ws-restful-server/rest/notaFiscalService/enviar";
	
	public static void main(String[] args) {
		try {
			tratarRespostaWebService(solicitarWebService(NotaFiscal.create()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Faz as devidas verificações da resposta do WebService e imprime o XML
	 * 
	 * @param response
	 */
	private static void tratarRespostaWebService(ClientResponse response) {
		if (response.getStatus() != 200) {
			throw new RuntimeException("Deu pau : HTTP com código de erro : " + response.getStatus());
		}

		String nfe = response.getEntity(String.class);
		System.out.println("Resposta: \n" + fromXML(nfe));
	}

	/**
	 * Faz a conexão com o webservice e envia a NotaFiscal em XML
	 * 
	 * @param nfe
	 * @return ClientResponse
	 */
	private static ClientResponse solicitarWebService(NotaFiscal nfe) {
		Client client = Client.create();
		WebResource webResource = client.resource(WEB_SERVICE_OOBJ);
		
		String xml = toXML(nfe);
		System.out.println("Enviando o XML: \n\n" + xml);

		return webResource.type(MediaType.APPLICATION_XML).post(ClientResponse.class, xml);
	}
	
	/**
	 * Obtém o objeto NotaFiscal através de um XML em String
	 * 
	 * @param xml
	 * @return NotaFiscal
	 */
	private static NotaFiscal fromXML(String xml) {
		XStream xstream = getXStream();
		return (NotaFiscal) xstream.fromXML(xml);
	}
	
	/**
	 * Converte o objeto NotaFiscal em um XML para devolver no WS
	 * 
	 * @param nfe
	 * @return String (xml)
	 */
	private static String toXML(NotaFiscal nfe) {
		XStream xstream = getXStream();
		return xstream.toXML(nfe);
	}
	
	/**
	 * Cria uma instância do XStream, faz com que a instância leia as anotações automaticamente e o retorna 
	 * 
	 * @return XStream
	 */
	private static XStream getXStream() {
		XStream xstream = new XStream(new DomDriver());
		//xstream.autodetectAnnotations(Boolean.TRUE);
		return xstream;
	}
}
