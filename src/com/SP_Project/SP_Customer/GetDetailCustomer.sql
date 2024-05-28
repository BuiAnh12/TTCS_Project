CREATE PROCEDURE GetDetailCustomers
    @CustomerId INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        p.ProductName, 
        ii.Quantity, 
        p.SellPrice, 
        ii.TotalPrice 
    FROM 
        Invoice_Items ii
        JOIN Invoices i ON ii.InvoiceId = i.InvoiceId
        JOIN Customers c ON i.CustomerId = c.CustomerId
        JOIN Imports im ON ii.ImportId = im.ImportId
        JOIN Products p ON p.ProductId = im.ProductId
    WHERE 
        c.CustomerId = @CustomerId;
END


