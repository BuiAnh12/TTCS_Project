CREATE PROCEDURE DeleteInvoice
    @InvoiceId INT
AS
BEGIN
    SET NOCOUNT ON;

    DELETE FROM Invoices
    WHERE InvoiceId = @InvoiceId;
END