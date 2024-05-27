
/**
 * Author:  AnTran
 * Created: May 26, 2024
 */
CREATE PROCEDURE [dbo].[GetInvoiceInfo]
    @InvoiceId INT
AS
BEGIN
    SELECT
    Staffs.Name, 
    Customers.CustomerName, 
	SUM(ii.TotalPrice) AS TotalPrice, 
    ii.CreatedAt
    
FROM 
    Staffs
JOIN 
    Invoices ON Staffs.StaffId = Invoices.StaffId
JOIN 
    Customers ON Customers.CustomerId = Invoices.CustomerId
JOIN 
    Invoice_Items AS ii ON ii.InvoiceId = Invoices.InvoiceId
WHERE 
    Invoices.InvoiceId = @InvoiceId
GROUP BY 
    Staffs.Name, 
    Customers.CustomerName, 
    ii.TotalPrice, 
    ii.CreatedAt;
END;
