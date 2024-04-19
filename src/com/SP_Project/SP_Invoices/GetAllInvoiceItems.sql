CREATE PROCEDURE GetAllInvoiceItems
AS
BEGIN
    SET NOCOUNT ON;

    SELECT *
    FROM Invoice_Items;
END