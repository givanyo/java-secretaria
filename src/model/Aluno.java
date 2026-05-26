package model;

public class Aluno {

    private int id;
    private String nomeAluno;
    private String cpf;
    private String dataNascimento;
    private String genero;
    private String nomeResponsavel;
    private Boolean afro;
    private Boolean escolaridadePublica;

    public Aluno() {
    }

    public Aluno(String nomeAluno, String cpf, String dataNascimento, String genero,
                 String nomeResponsavel, Boolean afro, Boolean escolaridadePublica) {
        this.nomeAluno = nomeAluno;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.nomeResponsavel = nomeResponsavel;
        this.afro = afro;
        this.escolaridadePublica = escolaridadePublica;
    }

    public Aluno(int id, String nomeAluno, String cpf, String dataNascimento, String genero,
                 String nomeResponsavel, Boolean afro, Boolean escolaridadePublica) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.nomeResponsavel = nomeResponsavel;
        this.afro = afro;
        this.escolaridadePublica = escolaridadePublica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public Boolean getAfro() {
        return this.afro;
    }

    public void setAfro(Boolean afro) {
        this.afro = afro;
    }

    public Boolean getEscolaridadePublica() {
        return this.escolaridadePublica;
    }

    public void setEscolaridadePublica(Boolean escolaridadePublica) {
        this.escolaridadePublica = escolaridadePublica;
    }
}
