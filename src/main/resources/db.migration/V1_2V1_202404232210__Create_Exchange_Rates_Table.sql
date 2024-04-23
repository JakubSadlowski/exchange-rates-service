if not exists (Select top 1 1 from sys.databases where name='exchange_rates')
begin
    exec('CREATE DATABASE exchange_rates');
end
USE exchange_rates;

if object_id('exchange_rates.dbo.currencies') is null begin
CREATE TABLE dbo.currencies(
c_currency char(3) NOT NULL,
constraint c_pk primary key clustered (c_currency asc)
);
end

if object_id('exchange_rates.dbo.rates') is null begin
CREATE TABLE dbo.rates (
er_id int NOT NULL,
er_currency_from char(3) NOT NULL,
er_currency_to char(3) NOT NULL,
er_rate decimal(7, 4) NOT NULL,
er_date date NOT NULL,
er_date_inserted datetime2(7) NOT NULL,
er_date_modified datetime2(7) NOT NULL,
constraint er_pk primary key clustered (er_id asc)
);
create unique nonclustered index er_uk on dbo.rates(er_currency_from,er_currency_to,er_rate,er_date);
alter table dbo.rates add constraint df_rates_er_date_inserted default (getdate()) for er_date_inserted;
alter table dbo.rates add constraint df_rates_er_date_modified default (getdate()) for er_date_modified;
alter table dbo.rates with check
add constraint fk_rates_currencies_from
foreign key(er_currency_from)
references dbo.currencies (c_currency);
--alter table dbo.rates check constraint fk_rates_currencies_to;
end

if not exists (select top 1 1 from exchange_rates.dbo.currencies where c_currency='PLN') begin
    insert into exchange_rates.dbo.currencies(c_currency) values('PLN');
end
if not exists (select top 1 1 from exchange_rates.dbo.currencies where c_currency='EUR') begin
    insert into exchange_rates.dbo.currencies(c_currency) values('EUR');
end
if not exists (select top 1 1 from exchange_rates.dbo.currencies where c_currency='USD') begin
    insert into exchange_rates.dbo.currencies(c_currency) values('USD');
end

