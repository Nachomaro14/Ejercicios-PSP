import java.util.Date;

public class Empleados {
    private static final long serialVersionUID = 1L;
    private int empNo;
    private Departamentos departamentos;
    private String apellido;
    private String oficio;
    private int dir;
    private Date fechaAlt;
    private Float salario;
    private Float comision;
    
    public Empleados(){
        
    }
    
    public Empleados(int empNo, Departamentos departamentos){
        this.empNo = empNo;
        this.departamentos = departamentos;
    }
    
    public Empleados(int empNo, Departamentos departamentos, String apellido, String oficio, int dir, Date fechaAlt, Float salario, Float comision){
        this.empNo = empNo;
        this.departamentos = departamentos;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fechaAlt = fechaAlt;
        this.salario = salario;
        this.comision = comision;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getEmpNo() {
        return empNo;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public String getApellido() {
        return apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public int getDir() {
        return dir;
    }

    public Date getFechaAlt() {
        return fechaAlt;
    }

    public Float getSalario() {
        return salario;
    }

    public Float getComision() {
        return comision;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setFechaAlt(Date fechaAlt) {
        this.fechaAlt = fechaAlt;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }
}
