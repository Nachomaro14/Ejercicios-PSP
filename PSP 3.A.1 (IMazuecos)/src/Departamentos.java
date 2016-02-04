public class Departamentos {
    private static final long serialVersionUID = 1L;
    private int deptNo;
    private String dnombre;
    private String loc;
    
    public Departamentos(){
    }
    
    public Departamentos(int deptNo){
        this.deptNo = deptNo;
    }
    
    public Departamentos(int deptNo, String dnombre, String loc){
        this.deptNo = deptNo;
        this.dnombre = dnombre;
        this.loc = loc;
    }
    
    public int getDeptNo(){
        return this.deptNo;
    }
    
    public void setDeptNo(int i){
        this.deptNo = i;
    }
    
    public String getDnombre(){
        return this.dnombre;
    }
    
    public void setDnombre(String dnombre){
        this.dnombre = dnombre;
    }
    
    public String getLoc(){
        return this.loc;
    }
    
    public void setLoc(String loc){
        this.loc = loc;
    }
}
