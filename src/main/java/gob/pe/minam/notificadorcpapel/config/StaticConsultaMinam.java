package gob.pe.minam.notificadorcpapel.config;

import gob.pe.minam.notificadorcpapel.model.Consulta;

public class StaticConsultaMinam {

    private Consulta consulta;
    private static StaticConsultaMinam instancia;
    public StaticConsultaMinam(Consulta consulta){
        this.consulta=consulta;

    }
    public  static StaticConsultaMinam getInstancia(Consulta consulta) {
        try {
            if (instancia==null) {
                instancia=new StaticConsultaMinam(consulta);
            }
        } catch (Exception e) {
            e.getMessage();

        }
        return instancia;
    }
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
}
