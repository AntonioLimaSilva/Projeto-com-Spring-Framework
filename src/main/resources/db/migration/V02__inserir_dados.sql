/** DADOS DA TABELA NPJ_REAL_ESTADO  **/
INSERT INTO [NPJ_REAL_estado] (nome, sigla) VALUES ('Acre', 'AC');

/** DADOS DA TABELA NPJ_REAL_CIDADE  **/
INSERT INTO [NPJ_REAL_cidade] (nome, id_estado) VALUES ('Acrelândia', 1);

/** DADOS DA TABELA NPJ_REAL_ENDERECO  **/
INSERT INTO [NPJ_REAL_endereco] (bairro, cep, logradouro, id_cidade) VALUES ('Dom José', '19.999-100', 'Av. Tal', 1);

/** DADOS DA TABELA GLOBAL_PESSOA  **/
INSERT INTO [GLOBAL_pessoa] (nome, pseudonimo, atendimento_preferencial, content_type, cpf, data_criacao, data_nascimento, email, 
estado_civil, fone_celular, fone_residencial, foto, idoso, nacionalidade, naturalidade, observacao, portador_deficiencia, profissao,
rg, sexo, id_endereco) VALUES ('Antonio Luciano Lima da Silva', 'Lima', 0, 'image/jpeg', '035.639.833-13', '2018-02-02 17:56:53.7620000',
'1988-11-23', 'luclimasilva23@gmail.com', 'SOLTEIRO', '(98) 8527-485', '(88) 3112-485', 
'0592cbb8-745f-40d7-bbde-40766ba37310_18319038_1054596248010942_4313905091700824295_o.jpg', 0, 'Brasileiro', 'Sobral', 'Teste',
0, 'Programador Java', '2012201081010', 'MASCULINO', 1);

/** DADOS DA TABELA NPJ_REAL_USUARIO  **/
INSERT INTO [NPJ_REAL_usuario] (ativo, data_criacao, login, senha, id_pessoa) 
VALUES (1, '2018-05-13 17:56:53.7620000', 'Luciano', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 1);

/** DADOS DA TABELA NPJ_REAL_TURMA_GRUPO  **/
INSERT INTO [NPJ_REAL_grupo] (nome) VALUES ('Administrador(a)');

/** DADOS DA TABELA NPJ_REAL_TURMA_PERMISSAO  **/
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_ACAO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_ACAO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_ACAO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_ACOES');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_ALUNO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_ALUNO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_ALUNO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_ALUNOS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_ASSISTIDO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_ASSISTIDO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_ASSISTIDO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_ASSISTIDOS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_CARGA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_CARGA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_CARGA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_CARGAS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_DISCIPLINA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_DISCIPLINA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_DISCIPLINA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_DISCIPLINAS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_DOCUMENTO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_DOCUMENTO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_DOCUMENTO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_DOCUMENTOS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_EXPEDIENTE');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_EXPEDIENTE');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_EXPEDIENTE');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_EXPEDIENTES');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_FUNCIONARIO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_FUNCIONARIO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_FUNCIONARIO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_FUNCIONARIOS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_GRUPO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_GRUPO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_GRUPO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_GRUPOS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_PESSOA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_PESSOA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_PESSOA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_PESSOAS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_PROCESSO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_PROCESSO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_PROCESSO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_PROCESSOS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_REU');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_REU');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_REU');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_REUS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_MASTER');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_TURMA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_TURMA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_TURMA');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_TURMAS');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_CAD_USUARIO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EDI_USUARIO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_EXC_USUARIO');
INSERT INTO [NPJ_REAL_permissao] (nome) VALUES ('ROLE_PES_USUARIOS');

/** DADOS DA TABELA NPJ_REAL_USUARIO_GRUPO  **/
INSERT INTO [NPJ_REAL_usuario_grupo] (id_usuario, id_grupo) VALUES (1, 1);

/** DADOS DA TABELA NPJ_REAL_GRUPO_PERMISSAO  **/
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 1);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 2);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 3);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 4);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 5);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 6);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 7);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 8);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 9);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 10);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 11);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 12);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 13);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 14);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 15);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 16);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 17);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 18);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 19);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 20);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 21);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 22);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 23);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 24);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 25);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 26);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 27);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 28);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 29);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 30);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 31);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 32);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 33);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 34);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 35);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 36);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 37);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 38);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 39);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 40);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 41);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 42);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 43);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 44);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 45);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 46);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 47);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 48);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 49);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 50);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 51);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 52);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 53);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 54);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 55);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 56);
INSERT INTO [NPJ_REAL_grupo_permissao] (id_grupo, id_permissao) VALUES (1, 57);

/** DADOS DA TABELA NPJ_REAL_ACAO  **/
INSERT INTO [NPJ_REAL_acao] (nome, data_criacao) VALUES ('AÇÃO DE ALIMENTOS', '2018-02-02 17:56:53.7620000');
INSERT INTO [NPJ_REAL_acao] (nome, data_criacao) VALUES ('AÇÃO DE DIVORCIO', '2018-02-02 17:56:53.7620000');

/** DADOS DA TABELA NPJ_REAL_DISCIPLINA  **/
INSERT INTO [NPJ_REAL_disciplina] (nome, data_criacao) VALUES ('Estágio IV', '2018-04-02 13:00:00.7620000');
INSERT INTO [NPJ_REAL_disciplina] (nome, data_criacao) VALUES ('Estágio V', '2018-04-02 13:00:00.7620000');

/** DADOS DA TABELA NPJ_REAL_TURMA  **/
INSERT INTO [NPJ_REAL_turma] (nome) VALUES ('TURMA AB MANHÃ');
INSERT INTO [NPJ_REAL_turma] (nome) VALUES ('TURMA CD MANHÃ');
INSERT INTO [NPJ_REAL_turma] (nome) VALUES ('TURMA AB TARDE');
INSERT INTO [NPJ_REAL_turma] (nome) VALUES ('TURMA CD TARDE');

/** DADOS DA TABELA NPJ_REAL_HORARIO  **/
INSERT INTO [NPJ_REAL_horario] (data) VALUES ('2018-04-16 08:00:00.7620000');
INSERT INTO [NPJ_REAL_horario] (data) VALUES ('2018-04-16 10:00:00.7620000');
INSERT INTO [NPJ_REAL_horario] (data) VALUES ('2018-04-17 08:00:00.7620000');
INSERT INTO [NPJ_REAL_horario] (data) VALUES ('2018-04-17 10:00:00.7620000');
INSERT INTO [NPJ_REAL_horario] (data) VALUES ('2018-04-18 10:00:00.7620000');
INSERT INTO [NPJ_REAL_horario] (data) VALUES ('2018-04-18 10:00:00.7620000');

/** DADOS DA TABELA NPJ_REAL_TURMA_HORARIO  **/
INSERT INTO [NPJ_REAL_turma_horario] (id_turma, id_horario) VALUES (1, 1);
INSERT INTO [NPJ_REAL_turma_horario] (id_turma, id_horario) VALUES (1, 3);
INSERT INTO [NPJ_REAL_turma_horario] (id_turma, id_horario) VALUES (1, 5);
INSERT INTO [NPJ_REAL_turma_horario] (id_turma, id_horario) VALUES (2, 2);
INSERT INTO [NPJ_REAL_turma_horario] (id_turma, id_horario) VALUES (2, 4);
INSERT INTO [NPJ_REAL_turma_horario] (id_turma, id_horario) VALUES (2, 6);

/** DADOS DA TABELA NPJ_REAL_TURMA_EQUIPE  **/
INSERT INTO [NPJ_REAL_equipe] (nome, data_criacao) VALUES ('EQUIPE A', '2018-02-02 17:56:53.7620000');
INSERT INTO [NPJ_REAL_equipe] (nome, data_criacao) VALUES ('EQUIPE B', '2018-02-02 17:56:53.7620000');

/** DADOS DA TABELA NPJ_REAL_TURMA_CABINE  **/
INSERT INTO [NPJ_REAL_cabine] (nome, data_criacao) VALUES ('CABINE 01', '2018-02-02 17:56:53.7620000');
INSERT INTO [NPJ_REAL_cabine] (nome, data_criacao) VALUES ('CABINE 02', '2018-02-02 17:56:53.7620000');