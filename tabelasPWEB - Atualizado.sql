USE [trabPWeb]
GO
/****** Object:  Table [dbo].[tbPerfil]    Script Date: 05/25/2014 10:40:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbPerfil](
	[IdPerfil] [int] NOT NULL,
	[Descricao] [varchar](50) NULL,
 CONSTRAINT [PK_tbPerfil] PRIMARY KEY CLUSTERED 
(
	[IdPerfil] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbLogin]    Script Date: 05/25/2014 10:40:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbLogin](
	[IdLogin] [int] NOT NULL,
	[Login] [varchar](50) NULL,
	[Senha] [varchar](50) NULL,
	[IdPerfil] [int] NOT NULL,
 CONSTRAINT [PK_tbLogin] PRIMARY KEY CLUSTERED 
(
	[IdLogin] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbDisciplina]    Script Date: 05/25/2014 10:40:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbDisciplina](
	[IdDisciplina] [int] NOT NULL,
	[Nome] [varchar](50) NULL,
	[Carga_Horaria] [varchar](50) NULL,
 CONSTRAINT [PK_tbDisciplina] PRIMARY KEY CLUSTERED 
(
	[IdDisciplina] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbCurso]    Script Date: 05/25/2014 10:40:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbCurso](
	[IdCurso] [int] NOT NULL,
	[Nome] [varchar](50) NULL,
	[Duracao] [varchar](50) NULL,
	[Modalidade] [varchar](50) NULL,
	[IdDisciplina] [int] NULL,
 CONSTRAINT [PK_tbCurso] PRIMARY KEY CLUSTERED 
(
	[IdCurso] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbAlunoDisciplina]    Script Date: 05/25/2014 10:40:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbAlunoDisciplina](
	[IdAluno] [int] NOT NULL,
	[IdDisciplina] [int] NOT NULL,
	[Faltas] [int] NULL,
	[Nota] [float] NULL,
 CONSTRAINT [PK_tbAlunoDisciplina] PRIMARY KEY CLUSTERED 
(
	[IdAluno] ASC,
	[IdDisciplina] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbAluno]    Script Date: 05/25/2014 10:40:41 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbAluno](
	[IdAluno] [int] NOT NULL,
	[RA] [varchar](50) NULL,
	[RG] [varchar](50) NULL,
	[CPF] [varchar](50) NULL,
	[Nome] [varchar](50) NULL,
	[Endereco] [varchar](50) NULL,
	[DtNasc] [datetime] NULL,
	[IdCurso] [int] NULL,
 CONSTRAINT [PK_tbAluno] PRIMARY KEY CLUSTERED 
(
	[IdAluno] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
