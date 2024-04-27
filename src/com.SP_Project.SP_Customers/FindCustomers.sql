CREATE PROCEDURE FindListCustomer
    @Name NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        Customers.CustomerId, 
        Customers.CustomerName, 
        Customers.Email, 
        Customers.Address, 
        SUM(Invoice_Items.TotalPrice) AS TotalAmount
    FROM 
        Customers 
        JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId 
        JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId 
    WHERE 
        Customers.CustomerName LIKE '%' + @Name + '%'
    GROUP BY 
        Customers.CustomerId, 
        Customers.CustomerName, 
        Customers.Email, 
        Customers.Address;
END

