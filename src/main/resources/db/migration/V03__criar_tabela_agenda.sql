CREATE TABLE [dbo].[NPJ_REAL_agenda](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[data_inicio] [datetime2](7) NOT NULL,
	[data_fim] [datetime2](7) NOT NULL,
	[assistido] [varchar](200) NOT NULL,
	[color] [varchar](100) NOT NULL,
	CONSTRAINT [pk_agenda] PRIMARY KEY CLUSTERED ([id] ASC)
);