CREATE PROCEDURE GetCustomerData
    @Status INT,
    @CustomerNameFilter NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    IF @Status = 1
    BEGIN
        SELECT 
            Customers.CustomerId, 
            Customers.CustomerName, 
            Customers.Email, 
            Customers.Address, 
            SUM(Invoice_Items.TotalPrice) AS TotalAmount 
        FROM 
            Customers 
            LEFT JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId 
            LEFT JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId 
        WHERE 
            Customers.CustomerName LIKE '%' + @CustomerNameFilter + '%'
        GROUP BY 
            Customers.CustomerId, 
            Customers.CustomerName, 
            Customers.Email, 
            Customers.Address 
        ORDER BY 
            Customers.CustomerName;
    END
    ELSE IF @Status = 2
    BEGIN
        SELECT 
            Customers.CustomerId, 
            Customers.CustomerName, 
            Customers.Email, 
            Customers.Address, 
            SUM(Invoice_Items.TotalPrice) AS TotalAmount 
        FROM 
            Customers 
            LEFT JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId 
            LEFT JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId 
        WHERE 
            Customers.CustomerName LIKE '%' + @CustomerNameFilter + '%'
        GROUP BY 
            Customers.CustomerId, 
            Customers.CustomerName, 
            Customers.Email, 
            Customers.Address 
        ORDER BY 
            Customers.Email;
    END
    ELSE IF @Status = 3
    BEGIN
        SELECT 
            Customers.CustomerId, 
            Customers.CustomerName, 
            Customers.Email, 
            Customers.Address, 
            SUM(Invoice_Items.TotalPrice) AS TotalAmount 
        FROM 
            Customers 
            LEFT JOIN Invoices ON Customers.CustomerId = Invoices.CustomerId 
            LEFT JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId 
        WHERE 
            Customers.CustomerName LIKE '%' + @CustomerNameFilter + '%'
        GROUP BY 
            Customers.CustomerId, 
            Customers.CustomerName, 
            Customers.Email, 
            Customers.Address 
        ORDER BY 
            TotalAmount;
    END
END
