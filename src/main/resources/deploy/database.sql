CREATE DATABASE exchange_rates;

USE exchange_rates;

CREATE TABLE dbo.currencies(
	c_currency char(3) NOT NULL,
    CONSTRAINT c_pk PRIMARY KEY CLUSTERED (c_currency ASC)
 );

CREATE TABLE dbo.rates(
	er_id int NOT NULL,
	er_currency_from char(3) NOT NULL,
	er_currency_to char(3) NOT NULL,
	er_rate decimal(7, 4) NOT NULL,
	er_date date NOT NULL,
	er_date_inserted datetime2(7) NOT NULL,
	er_date_modified datetime2(7) NOT NULL,
    CONSTRAINT er_pk PRIMARY KEY CLUSTERED (er_id ASC)
 );

CREATE UNIQUE NONCLUSTERED INDEX er_uk ON dbo.rates
(
	er_currency_from ASC,
	er_currency_to ASC,
	er_rate ASC,
	er_date ASC
);
ALTER TABLE dbo.rates ADD  CONSTRAINT DF_rates_er_date_inserted  DEFAULT (getdate()) FOR er_date_inserted;
ALTER TABLE dbo.rates ADD  CONSTRAINT DF_rates_er_date_modified  DEFAULT (getdate()) FOR er_date_modified;
ALTER TABLE dbo.rates  WITH CHECK ADD  CONSTRAINT fk_rates_currencies_from FOREIGN KEY(er_currency_from)
REFERENCES dbo.currencies (c_currency);
ALTER TABLE dbo.rates CHECK CONSTRAINT fk_rates_currencies_from;
ALTER TABLE dbo.rates  WITH CHECK ADD  CONSTRAINT fk_rates_currencies_to FOREIGN KEY(er_currency_to)
REFERENCES dbo.currencies (c_currency);
ALTER TABLE dbo.rates CHECK CONSTRAINT fk_rates_currencies_to;

if not exists (select top 1 1 from exchange_rates.dbo.currencies where c_currency='PLN') begin
	insert into exchange_rates.dbo.currencies(c_currency) values('PLN');
end
if not exists (select top 1 1 from exchange_rates.dbo.currencies where c_currency='EUR') begin
	insert into exchange_rates.dbo.currencies(c_currency) values('EUR');
end
if not exists (select top 1 1 from exchange_rates.dbo.currencies where c_currency='USD') begin
	insert into exchange_rates.dbo.currencies(c_currency) values('USD');
end
