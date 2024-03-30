package com.hyh.pojo;

public class Dleft {
    public int empno;
    public String dname;
    public int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Dleft() {
    }

    public Dleft(int empno, String dname, int rank) {
        this.empno = empno;
        this.dname = dname;
        this.rank = rank;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
