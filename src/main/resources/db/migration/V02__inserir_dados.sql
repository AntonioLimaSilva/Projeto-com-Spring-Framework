/** DADOS DA TABELA NPJ_REAL_ESTADO  **/
insert into NPJ_REAL_estado (nome, sigla) values ('Acre', 'AC');

/** DADOS DA TABELA NPJ_REAL_CIDADE  **/
insert into NPJ_REAL_cidade (nome, id_estado) values ('Acrelândia', 1);

/** DADOS DA TABELA NPJ_REAL_ENDERECO  **/
insert into NPJ_REAL_endereco (bairro, cep, logradouro, id_cidade) values ('Dom José', '19.999-100', 'Av. Tal', 1);

/** DADOS DA TABELA GLOBAL_PESSOA  **/
insert into GLOBAL_pessoa (nome, pseudonimo, atendimento_preferencial, content_type, cpf, data_criacao, data_nascimento, email, 
estado_civil, fone_celular, fone_residencial, foto, idoso, nacionalidade, naturalidade, observacao, portador_deficiencia, profissao,
rg, sexo, id_endereco) values ('Antonio Luciano Lima da Silva', 'Lima', 0, 'image/jpeg', '035.639.833-13', '2018-02-02 17:56:53.7620000',
'1988-11-23', 'luclimasilva23@gmail.com', 'SOLTEIRO', '(98) 8527-485', '(88) 3112-485', 
'0592cbb8-745f-40d7-bbde-40766ba37310_18319038_1054596248010942_4313905091700824295_o.jpg', 0, 'Brasileiro', 'Sobral', 'Teste',
0, 'Programador Java', '2012201081010', 'MASCULINO', 1);

/** DADOS DA TABELA NPJ_REAL_USUARIO  **/
insert into NPJ_REAL_usuario (ativo, data_criacao, login, senha, id_pessoa) 
values (1, '2018-05-13 17:56:53.7620000', 'Luciano', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.', 1);

/** DADOS DA TABELA NPJ_REAL_TURMA_GRUPO  **/
insert into NPJ_REAL_grupo (nome) values ('Administrador(a)');

/** DADOS DA TABELA NPJ_REAL_TURMA_PERMISSAO  **/
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_ACAO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_ACAO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_ACAO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_ACOES');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_ALUNO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_ALUNO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_ALUNO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_ALUNOS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_ASSISTIDO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_ASSISTIDO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_ASSISTIDO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_ASSISTIDOS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_CARGA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_CARGA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_CARGA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_CARGAS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_DISCIPLINA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_DISCIPLINA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_DISCIPLINA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_DISCIPLINAS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_DOCUMENTO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_DOCUMENTO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_DOCUMENTO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_DOCUMENTOS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_EXPEDIENTE');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_EXPEDIENTE');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_EXPEDIENTE');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_EXPEDIENTES');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_FUNCIONARIO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_FUNCIONARIO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_FUNCIONARIO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_FUNCIONARIOS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_GRUPO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_GRUPO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_GRUPO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_GRUPOS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_PESSOA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_PESSOA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_PESSOA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_PESSOAS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_PROCESSO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_PROCESSO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_PROCESSO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_PROCESSOS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_REU');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_REU');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_REU');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_REUS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_MASTER');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_TURMA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_TURMA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_TURMA');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_TURMAS');
insert into NPJ_REAL_permissao (nome) values ('ROLE_CAD_USUARIO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EDI_USUARIO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_EXC_USUARIO');
insert into NPJ_REAL_permissao (nome) values ('ROLE_PES_USUARIOS');

/** DADOS DA TABELA NPJ_REAL_USUARIO_GRUPO  **/
insert into NPJ_REAL_usuario_grupo (id_usuario, id_grupo) values (1, 1);

/** DADOS DA TABELA NPJ_REAL_GRUPO_PERMISSAO  **/
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 1);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 2);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 3);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 4);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 5);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 6);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 7);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 8);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 9);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 10);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 11);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 12);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 13);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 14);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 15);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 16);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 17);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 18);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 19);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 20);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 21);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 22);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 23);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 24);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 25);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 26);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 27);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 28);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 29);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 30);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 31);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 32);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 33);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 34);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 35);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 36);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 37);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 38);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 39);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 40);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 41);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 42);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 43);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 44);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 45);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 46);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 47);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 48);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 49);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 50);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 51);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 52);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 53);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 54);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 55);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 56);
insert into NPJ_REAL_grupo_permissao (id_grupo, id_permissao) values (1, 57);

/** DADOS DA TABELA NPJ_REAL_ACAO  **/
insert into NPJ_REAL_acao (nome, data_criacao) values ('AÇÃO DE ALIMENTOS', '2018-02-02 17:56:53.7620000');
insert into NPJ_REAL_acao (nome, data_criacao) values ('AÇÃO DE DIVORCIO', '2018-02-02 17:56:53.7620000');

/** DADOS DA TABELA NPJ_REAL_DISCIPLINA  **/
insert into NPJ_REAL_disciplina (nome, data_criacao) values ('Estágio IV', '2018-04-02 13:00:00.7620000');
insert into NPJ_REAL_disciplina (nome, data_criacao) values ('Estágio V', '2018-04-02 13:00:00.7620000');

/** DADOS DA TABELA NPJ_REAL_TURMA  **/
insert into NPJ_REAL_turma (nome) values ('TURMA AB MANHÃ');
insert into NPJ_REAL_turma (nome) values ('TURMA CD MANHÃ');
insert into NPJ_REAL_turma (nome) values ('TURMA AB TARDE');
insert into NPJ_REAL_turma (nome) values ('TURMA CD TARDE');

/** DADOS DA TABELA NPJ_REAL_HORARIO  **/
insert into NPJ_REAL_horario (data) values ('2018-04-16 08:00:00.7620000');
insert into NPJ_REAL_horario (data) values ('2018-04-16 10:00:00.7620000');
insert into NPJ_REAL_horario (data) values ('2018-04-17 08:00:00.7620000');
insert into NPJ_REAL_horario (data) values ('2018-04-17 10:00:00.7620000');
insert into NPJ_REAL_horario (data) values ('2018-04-18 10:00:00.7620000');
insert into NPJ_REAL_horario (data) values ('2018-04-18 10:00:00.7620000');

/** DADOS DA TABELA NPJ_REAL_TURMA_HORARIO  **/
insert into NPJ_REAL_turma_horario (id_turma, id_horario) values (1, 1);
insert into NPJ_REAL_turma_horario (id_turma, id_horario) values (1, 3);
insert into NPJ_REAL_turma_horario (id_turma, id_horario) values (1, 5);
insert into NPJ_REAL_turma_horario (id_turma, id_horario) values (2, 2);
insert into NPJ_REAL_turma_horario (id_turma, id_horario) values (2, 4);
insert into NPJ_REAL_turma_horario (id_turma, id_horario) values (2, 6);

/** DADOS DA TABELA NPJ_REAL_TURMA_EQUIPE  **/
insert into NPJ_REAL_equipe (nome, data_criacao) values ('EQUIPE A', '2018-02-02 17:56:53.7620000');
insert into NPJ_REAL_equipe (nome, data_criacao) values ('EQUIPE B', '2018-02-02 17:56:53.7620000');

/** DADOS DA TABELA NPJ_REAL_TURMA_CABINE  **/
insert into NPJ_REAL_cabine (nome, data_criacao) values ('CABINE 01', '2018-02-02 17:56:53.7620000');
insert into NPJ_REAL_cabine (nome, data_criacao) values ('CABINE 02', '2018-02-02 17:56:53.7620000');