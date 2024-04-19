CREATE PROCEDURE GetInvoicesByStatusAndName
    @Status INT,
    @Name NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    IF @Status = 1
    BEGIN
        SELECT DISTINCT 
            i.InvoiceId, 
            c.CustomerId, 
            s.StaffId, 
            i.PurchaseDate, 
            c.CustomerName, 
            s.Name AS StaffName, 
            SUM(ii.TotalPrice) AS TotalAmount 
        FROM 
            Invoices i
        JOIN 
            Customers c ON c.CustomerId = i.CustomerId 
        JOIN 
            Staffs s ON s.StaffId = i.StaffId 
        JOIN 
            Invoice_Items ii ON ii.InvoiceId = i.InvoiceId 
        WHERE 
            c.CustomerName LIKE '%' + @Name + '%'
        GROUP BY 
            i.InvoiceId, 
            c.CustomerId, 
            s.StaffId, 
            i.PurchaseDate, 
            c.CustomerName, 
            s.Name
        ORDER BY 
            c.CustomerName;
    END
    ELSE IF @Status = 2
    BEGIN
        SELECT DISTINCT 
            i.InvoiceId, 
            c.CustomerId, 
            s.StaffId, 
            i.PurchaseDate, 
            c.CustomerName, 
            s.Name AS StaffName, 
            SUM(ii.TotalPrice) AS TotalAmount 
        FROM 
            Invoices i
        JOIN 
            Customers c ON c.CustomerId = i.CustomerId 
        JOIN 
            Staffs s ON s.StaffId = i.StaffId 
        JOIN 
            Invoice_Items ii ON ii.InvoiceId = i.InvoiceId 
        WHERE 
            c.CustomerName LIKE '%' + @Name + '%'
        GROUP BY 
            i.InvoiceId, 
            c.CustomerId, 
            s.StaffId, 
            i.PurchaseDate, 
            c.CustomerName, 
            s.Name
        ORDER BY 
            s.Name;
    END
    ELSE IF @Status = 3
    BEGIN
        SELECT DISTINCT 
            i.InvoiceId, 
            c.CustomerId, 
            s.StaffId, 
            i.PurchaseDate, 
            c.CustomerName, 
            s.Name AS StaffName, 
            SUM(ii.TotalPrice) AS TotalAmount 
        FROM 
            Invoices i
        JOIN 
            Customers c ON c.CustomerId = i.CustomerId 
        JOIN 
            Staffs s ON s.StaffId = i.StaffId 
        JOIN 
            Invoice_Items ii ON ii.InvoiceId = i.InvoiceId 
        WHERE 
            c.CustomerName LIKE '%' + @Name + '%'
        GROUP BY 
            i.InvoiceId, 
            c.CustomerId, 
            s.StaffId, 
            i.PurchaseDate, 
            c.CustomerName, 
            s.Name
        ORDER BY 
            SUM(ii.TotalPrice);
    END
END