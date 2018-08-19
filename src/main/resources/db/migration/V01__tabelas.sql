/****** Object:  Table [dbo].[GLOBAL_pessoa]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[GLOBAL_pessoa](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[atendimento_preferencial] [bit] NOT NULL,
	[content_type] [varchar](255) NULL,
	[cpf] [varchar](255) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[data_nascimento] [date] NULL,
	[email] [varchar](255) NOT NULL,
	[estado_civil] [varchar](255) NOT NULL,
	[fone_celular] [varchar](255) NOT NULL,
	[fone_residencial] [varchar](255) NULL,
	[foto] [varchar](255) NULL,
	[idoso] [bit] NOT NULL,
	[nacionalidade] [varchar](255) NULL,
	[naturalidade] [varchar](255) NULL,
	[nome] [varchar](255) NOT NULL,
	[observacao] [varchar](600) NULL,
	[portador_deficiencia] [bit] NOT NULL,
	[profissao] [varchar](255) NULL,
	[pseudonimo] [varchar](255) NOT NULL,
	[rg] [varchar](255) NULL,
	[sexo] [varchar](255) NOT NULL,
	[id_endereco] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_acao]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_acao](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[nome] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_acao_processo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_acao_processo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[id_acao] [int] NOT NULL,
	[id_processo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_aluno]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_aluno](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[matricula] [varchar](20) NOT NULL,
	[id_cabine] [int] NOT NULL,
	[id_equipe] [int] NOT NULL,
	[id_expediente] [int] NOT NULL,
	[id_pessoa] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_aluno_processo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_aluno_processo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[id_aluno] [int] NOT NULL,
	[id_processo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_assistido]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_assistido](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[id_pessoa] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_assistido_processo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_assistido_processo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[tipo_parte] [varchar](50) NOT NULL,
	[id_assistido] [int] NOT NULL,
	[id_processo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_cabine]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_cabine](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_carga]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_carga](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[data_entrega] [datetime2](7) NULL,
	[data_saida] [datetime2](7) NOT NULL,
	[descricao] [varchar](255) NOT NULL,
	[id_pessoa] [int] NOT NULL,
	[id_processo] [int] NOT NULL,
	[id_usuario] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_cidade]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_cidade](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NOT NULL,
	[id_estado] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_disciplina]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_disciplina](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_documento]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_documento](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[content_type] [varchar](100) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[descricao] [varchar](255) NOT NULL,
	[nome] [varchar](255) NOT NULL,
	[principal] [bit] NOT NULL,
	[tamanho] [bigint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_documento_processo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_documento_processo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[id_documento] [int] NOT NULL,
	[id_processo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_endereco]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_endereco](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[bairro] [varchar](255) NULL,
	[cep] [varchar](255) NOT NULL,
	[logradouro] [varchar](255) NOT NULL,
	[id_cidade] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_equipe]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_equipe](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_estado]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_estado](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NOT NULL,
	[sigla] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_expediente]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_expediente](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ativo] [bit] NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[periodo_letivo] [varchar](20) NOT NULL,
	[id_disciplina] [int] NOT NULL,
	[id_funcionario] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_expediente_turma]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_expediente_turma](
	[id_expediente] [int] NOT NULL,
	[id_turma] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_funcionario]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_funcionario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[funcao] [varchar](255) NOT NULL,
	[id_pessoa] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_funcionario_processo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_funcionario_processo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[tipo_participacao] [varchar](100) NOT NULL,
	[id_funcionario] [int] NOT NULL,
	[id_processo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_grupo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_grupo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_grupo_permissao]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_grupo_permissao](
	[id_permissao] [int] NOT NULL,
	[id_grupo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_grupo] ASC,
	[id_permissao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_horario]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_horario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data] [datetime2](7) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_mensagem]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_mensagem](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[conteudo] [varchar](255) NOT NULL,
	[data_fim] [date] NOT NULL,
	[data_inicio] [date] NOT NULL,
	[titulo] [varchar](50) NOT NULL,
	[id_usuario_ativo] [int] NOT NULL,
	[id_usuario_passivo] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_permissao]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_permissao](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_processo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_processo](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[do_direito] [varchar](255) NULL,
	[do_fato] [varchar](255) NULL,
	[do_pedido] [varchar](255) NULL,
	[documentos_que_faltam] [varchar](255) NULL,
	[esta_com_aluno] [bit] NULL,
	[extinto] [bit] NULL,
	[localizacao] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_reu]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_reu](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[id_pessoa] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_turma]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_turma](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_turma_horario]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_turma_horario](
	[id_turma] [int] NOT NULL,
	[id_horario] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NPJ_REAL_usuario]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NPJ_REAL_usuario](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ativo] [bit] NOT NULL,
	[data_criacao] [datetime2](7) NOT NULL,
	[login] [varchar](255) NOT NULL,
	[senha] [varchar](255) NOT NULL,
	[id_pessoa] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NPJ_REAL_usuario_grupo]    Script Date: 12/05/2018 23:19:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NPJ_REAL_usuario_grupo](
	[id_usuario] [int] NOT NULL,
	[id_grupo] [int] NOT NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[GLOBAL_pessoa]  WITH CHECK ADD  CONSTRAINT [fk_pessoa_endereco] FOREIGN KEY([id_endereco])
REFERENCES [dbo].[NPJ_REAL_endereco] ([id])
GO
ALTER TABLE [dbo].[GLOBAL_pessoa] CHECK CONSTRAINT [fk_pessoa_endereco]
GO
ALTER TABLE [dbo].[NPJ_REAL_acao_processo]  WITH CHECK ADD  CONSTRAINT [fk_acao_processo_acao] FOREIGN KEY([id_acao])
REFERENCES [dbo].[NPJ_REAL_acao] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_acao_processo] CHECK CONSTRAINT [fk_acao_processo_acao]
GO
ALTER TABLE [dbo].[NPJ_REAL_acao_processo]  WITH CHECK ADD  CONSTRAINT [fk_acao_processo_processo] FOREIGN KEY([id_processo])
REFERENCES [dbo].[NPJ_REAL_processo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_acao_processo] CHECK CONSTRAINT [fk_acao_processo_processo]
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno]  WITH CHECK ADD  CONSTRAINT [fk_aluno_cabine] FOREIGN KEY([id_cabine])
REFERENCES [dbo].[NPJ_REAL_cabine] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno] CHECK CONSTRAINT [fk_aluno_cabine]
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno]  WITH CHECK ADD  CONSTRAINT [fk_aluno_equipe] FOREIGN KEY([id_equipe])
REFERENCES [dbo].[NPJ_REAL_equipe] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno] CHECK CONSTRAINT [fk_aluno_equipe]
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno]  WITH CHECK ADD  CONSTRAINT [fk_aluno_expediente] FOREIGN KEY([id_expediente])
REFERENCES [dbo].[NPJ_REAL_expediente] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno] CHECK CONSTRAINT [fk_aluno_expediente]
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno]  WITH CHECK ADD  CONSTRAINT [fk_aluno_pessoa] FOREIGN KEY([id_pessoa])
REFERENCES [dbo].[GLOBAL_pessoa] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno] CHECK CONSTRAINT [fk_aluno_pessoa]
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno_processo]  WITH CHECK ADD  CONSTRAINT [fk_aluno_processo_aluno] FOREIGN KEY([id_aluno])
REFERENCES [dbo].[NPJ_REAL_aluno] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno_processo] CHECK CONSTRAINT [fk_aluno_processo_aluno]
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno_processo]  WITH CHECK ADD  CONSTRAINT [fk_aluno_processo_processo] FOREIGN KEY([id_processo])
REFERENCES [dbo].[NPJ_REAL_processo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_aluno_processo] CHECK CONSTRAINT [fk_aluno_processo_processo]
GO
ALTER TABLE [dbo].[NPJ_REAL_assistido]  WITH CHECK ADD  CONSTRAINT [fk_assistido_pessoa] FOREIGN KEY([id_pessoa])
REFERENCES [dbo].[GLOBAL_pessoa] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_assistido] CHECK CONSTRAINT [fk_assistido_pessoa]
GO
ALTER TABLE [dbo].[NPJ_REAL_assistido_processo]  WITH CHECK ADD  CONSTRAINT [fk_assistido_processo_assistido] FOREIGN KEY([id_assistido])
REFERENCES [dbo].[NPJ_REAL_assistido] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_assistido_processo] CHECK CONSTRAINT [fk_assistido_processo_assistido]
GO
ALTER TABLE [dbo].[NPJ_REAL_assistido_processo]  WITH CHECK ADD  CONSTRAINT [fk_assistido_processo_processo] FOREIGN KEY([id_processo])
REFERENCES [dbo].[NPJ_REAL_processo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_assistido_processo] CHECK CONSTRAINT [fk_assistido_processo_processo]
GO
ALTER TABLE [dbo].[NPJ_REAL_carga]  WITH CHECK ADD  CONSTRAINT [fk_carga_pessoa] FOREIGN KEY([id_pessoa])
REFERENCES [dbo].[GLOBAL_pessoa] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_carga] CHECK CONSTRAINT [fk_carga_pessoa]
GO
ALTER TABLE [dbo].[NPJ_REAL_carga]  WITH CHECK ADD  CONSTRAINT [fk_carga_processo] FOREIGN KEY([id_processo])
REFERENCES [dbo].[NPJ_REAL_processo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_carga] CHECK CONSTRAINT [fk_carga_processo]
GO
ALTER TABLE [dbo].[NPJ_REAL_carga]  WITH CHECK ADD  CONSTRAINT [fk_carga_usuario] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[NPJ_REAL_usuario] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_carga] CHECK CONSTRAINT [fk_carga_usuario]
GO
ALTER TABLE [dbo].[NPJ_REAL_cidade]  WITH CHECK ADD  CONSTRAINT [fk_cidade_estado] FOREIGN KEY([id_estado])
REFERENCES [dbo].[NPJ_REAL_estado] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_cidade] CHECK CONSTRAINT [fk_cidade_estado]
GO
ALTER TABLE [dbo].[NPJ_REAL_documento_processo]  WITH CHECK ADD  CONSTRAINT [fk_documento_processo_documento] FOREIGN KEY([id_documento])
REFERENCES [dbo].[NPJ_REAL_documento] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_documento_processo] CHECK CONSTRAINT [fk_documento_processo_documento]
GO
ALTER TABLE [dbo].[NPJ_REAL_documento_processo]  WITH CHECK ADD  CONSTRAINT [fk_documento_processo_processo] FOREIGN KEY([id_processo])
REFERENCES [dbo].[NPJ_REAL_processo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_documento_processo] CHECK CONSTRAINT [fk_documento_processo_processo]
GO
ALTER TABLE [dbo].[NPJ_REAL_endereco]  WITH CHECK ADD  CONSTRAINT [fk_endereco_cidade] FOREIGN KEY([id_cidade])
REFERENCES [dbo].[NPJ_REAL_cidade] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_endereco] CHECK CONSTRAINT [fk_endereco_cidade]
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente]  WITH CHECK ADD  CONSTRAINT [fk_expediente_disciplina] FOREIGN KEY([id_disciplina])
REFERENCES [dbo].[NPJ_REAL_disciplina] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente] CHECK CONSTRAINT [fk_expediente_disciplina]
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente]  WITH CHECK ADD  CONSTRAINT [fk_expediente_funcionario] FOREIGN KEY([id_funcionario])
REFERENCES [dbo].[NPJ_REAL_funcionario] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente] CHECK CONSTRAINT [fk_expediente_funcionario]
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente_turma]  WITH CHECK ADD  CONSTRAINT [fk_expediente_turma_expediente] FOREIGN KEY([id_expediente])
REFERENCES [dbo].[NPJ_REAL_expediente] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente_turma] CHECK CONSTRAINT [fk_expediente_turma_expediente]
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente_turma]  WITH CHECK ADD  CONSTRAINT [fk_expediente_turma_turma] FOREIGN KEY([id_turma])
REFERENCES [dbo].[NPJ_REAL_turma] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_expediente_turma] CHECK CONSTRAINT [fk_expediente_turma_turma]
GO
ALTER TABLE [dbo].[NPJ_REAL_funcionario]  WITH CHECK ADD  CONSTRAINT [fk_funcionario_pessoa] FOREIGN KEY([id_pessoa])
REFERENCES [dbo].[GLOBAL_pessoa] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_funcionario] CHECK CONSTRAINT [fk_funcionario_pessoa]
GO
ALTER TABLE [dbo].[NPJ_REAL_funcionario_processo]  WITH CHECK ADD  CONSTRAINT [fk_funcionario_processo_funcionario] FOREIGN KEY([id_funcionario])
REFERENCES [dbo].[NPJ_REAL_funcionario] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_funcionario_processo] CHECK CONSTRAINT [fk_funcionario_processo_funcionario]
GO
ALTER TABLE [dbo].[NPJ_REAL_funcionario_processo]  WITH CHECK ADD  CONSTRAINT [fk_funcionario_processo_processo] FOREIGN KEY([id_processo])
REFERENCES [dbo].[NPJ_REAL_processo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_funcionario_processo] CHECK CONSTRAINT [fk_funcionario_processo_processo]
GO
ALTER TABLE [dbo].[NPJ_REAL_grupo_permissao]  WITH CHECK ADD  CONSTRAINT [fk_grupo_permissao_grupo] FOREIGN KEY([id_grupo])
REFERENCES [dbo].[NPJ_REAL_grupo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_grupo_permissao] CHECK CONSTRAINT [fk_grupo_permissao_grupo]
GO
ALTER TABLE [dbo].[NPJ_REAL_grupo_permissao]  WITH CHECK ADD  CONSTRAINT [fk_grupo_permissao_permissao] FOREIGN KEY([id_permissao])
REFERENCES [dbo].[NPJ_REAL_permissao] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_grupo_permissao] CHECK CONSTRAINT [fk_grupo_permissao_permissao]
GO
ALTER TABLE [dbo].[NPJ_REAL_mensagem]  WITH CHECK ADD  CONSTRAINT [fk_mensagem_usuario_ativo] FOREIGN KEY([id_usuario_ativo])
REFERENCES [dbo].[NPJ_REAL_usuario] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_mensagem] CHECK CONSTRAINT [fk_mensagem_usuario_ativo]
GO
ALTER TABLE [dbo].[NPJ_REAL_mensagem]  WITH CHECK ADD  CONSTRAINT [fk_mensagem_usuario_passivo] FOREIGN KEY([id_usuario_passivo])
REFERENCES [dbo].[NPJ_REAL_usuario] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_mensagem] CHECK CONSTRAINT [fk_mensagem_usuario_passivo]
GO
ALTER TABLE [dbo].[NPJ_REAL_reu]  WITH CHECK ADD  CONSTRAINT [fk_reu_pessoa] FOREIGN KEY([id_pessoa])
REFERENCES [dbo].[GLOBAL_pessoa] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_reu] CHECK CONSTRAINT [fk_reu_pessoa]
GO
ALTER TABLE [dbo].[NPJ_REAL_turma_horario]  WITH CHECK ADD  CONSTRAINT [fk_turma_horario_horario] FOREIGN KEY([id_horario])
REFERENCES [dbo].[NPJ_REAL_horario] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_turma_horario] CHECK CONSTRAINT [fk_turma_horario_horario]
GO
ALTER TABLE [dbo].[NPJ_REAL_turma_horario]  WITH CHECK ADD  CONSTRAINT [fk_turma_horario_turma] FOREIGN KEY([id_turma])
REFERENCES [dbo].[NPJ_REAL_turma] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_turma_horario] CHECK CONSTRAINT [fk_turma_horario_turma]
GO
ALTER TABLE [dbo].[NPJ_REAL_usuario]  WITH CHECK ADD  CONSTRAINT [fk_usuario_pessoa] FOREIGN KEY([id_pessoa])
REFERENCES [dbo].[GLOBAL_pessoa] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_usuario] CHECK CONSTRAINT [fk_usuario_pessoa]
GO
ALTER TABLE [dbo].[NPJ_REAL_usuario_grupo]  WITH CHECK ADD  CONSTRAINT [fk_usuario_grupo_grupo] FOREIGN KEY([id_grupo])
REFERENCES [dbo].[NPJ_REAL_grupo] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_usuario_grupo] CHECK CONSTRAINT [fk_usuario_grupo_grupo]
GO
ALTER TABLE [dbo].[NPJ_REAL_usuario_grupo]  WITH CHECK ADD  CONSTRAINT [fk_usuario_grupo_usuario] FOREIGN KEY([id_usuario])
REFERENCES [dbo].[NPJ_REAL_usuario] ([id])
GO
ALTER TABLE [dbo].[NPJ_REAL_usuario_grupo] CHECK CONSTRAINT [fk_usuario_grupo_usuario]
GO