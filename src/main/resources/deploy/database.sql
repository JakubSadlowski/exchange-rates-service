USE [exchange_rates]
GO
/****** Object:  Table [dbo].[currencies]    Script Date: 18/12/2023 21:44:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[currencies](
	[c_currency] [char](3) NOT NULL,
 CONSTRAINT [c_pk] PRIMARY KEY CLUSTERED
(
	[c_currency] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[rates]    Script Date: 18/12/2023 21:44:27 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[rates](
	[er_id] [int] NOT NULL,
	[er_currency_from] [char](3) NOT NULL,
	[er_currency_to] [char](3) NOT NULL,
	[er_rate] [decimal](7, 4) NOT NULL,
	[er_date] [date] NOT NULL,
	[er_date_inserted] [datetime2](7) NOT NULL,
	[er_date_modified] [datetime2](7) NOT NULL,
 CONSTRAINT [er_pk] PRIMARY KEY CLUSTERED
(
	[er_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [er_uk]    Script Date: 18/12/2023 21:44:27 ******/
CREATE UNIQUE NONCLUSTERED INDEX [er_uk] ON [dbo].[rates]
(
	[er_currency_from] ASC,
	[er_currency_to] ASC,
	[er_rate] ASC,
	[er_date] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[rates] ADD  CONSTRAINT [DF_rates_er_date_inserted]  DEFAULT (getdate()) FOR [er_date_inserted]
GO
ALTER TABLE [dbo].[rates] ADD  CONSTRAINT [DF_rates_er_date_modified]  DEFAULT (getdate()) FOR [er_date_modified]
GO
ALTER TABLE [dbo].[rates]  WITH CHECK ADD  CONSTRAINT [fk_rates_currencies_from] FOREIGN KEY([er_currency_from])
REFERENCES [dbo].[currencies] ([c_currency])
GO
ALTER TABLE [dbo].[rates] CHECK CONSTRAINT [fk_rates_currencies_from]
GO
ALTER TABLE [dbo].[rates]  WITH CHECK ADD  CONSTRAINT [fk_rates_currencies_to] FOREIGN KEY([er_currency_to])
REFERENCES [dbo].[currencies] ([c_currency])
GO
ALTER TABLE [dbo].[rates] CHECK CONSTRAINT [fk_rates_currencies_to]
GO
