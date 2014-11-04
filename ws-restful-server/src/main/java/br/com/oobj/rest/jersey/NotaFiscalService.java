package br.com.oobj.rest.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Path("/notaFiscalService")
public class NotaFiscalService {

	@POST
	@Path("/enviar")
	@Consumes(MediaType.APPLICATION_XML)
	public Response consomeXML(String nfeEmXML) {
		NotaFiscal nfe = fromXML(nfeEmXML);
		nfe = tratarNFe(nfe);
		
		return Response.status(200).entity(toXML(nfe)).build();
	}
	
	@GET
    @Path("/print/{empresa}")
    @Produces(MediaType.APPLICATION_XML)
    public String imprimirNotaDaEmpresa(@PathParam("empresa") String empresa) {
        return toXML(NotaFiscal.create(empresa));
    }

	/**
	 * Processa a nota fiscal e altera o status
	 * 
	 * @param nfe
	 * @return NotaFiscal
	 */
	private NotaFiscal tratarNFe(NotaFiscal nfe) {
		contarAte(150000);
		nfe.setStatus("AUTORIZADO");
		
		return nfe;
	}

	/**
	 * Conta de 0 até o valor recebido
	 * @param valor
	 */
	private void contarAte(int valor) {
		System.out.println("Contando até "+valor+" para fingir um processamento");
		for (int i=0; i<valor; i++) {
			System.out.println(i);
		}
		System.out.println("Fim da contagem");
	}
	
	/**
	 * Obtém o objeto NotaFiscal através de um XML em String
	 * 
	 * @param xml
	 * @return NotaFiscal
	 */
	private NotaFiscal fromXML(String xml) {
		XStream xstream = getXStream();
		return (NotaFiscal) xstream.fromXML(xml);
	}
	
	/**
	 * Converte o objeto NotaFiscal em um XML para devolver no WS
	 * 
	 * @param nfe
	 * @return String (xml)
	 */
	private String toXML(NotaFiscal nfe) {
		XStream xstream = getXStream();
		return xstream.toXML(nfe);
	}
	
	/**
	 * Cria uma instância do XStream, faz com que a instância leia as anotações automaticamente e o retorna 
	 * 
	 * @return XStream
	 */
	private XStream getXStream() {
		XStream xstream = new XStream(new DomDriver());
		//xstream.autodetectAnnotations(Boolean.TRUE);
		return xstream;
	}
}
