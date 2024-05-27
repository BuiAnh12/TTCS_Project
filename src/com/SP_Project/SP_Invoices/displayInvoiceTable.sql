
/**
 * Author:  AnTran
 * Created: May 27, 2024
 */
CREATE PROCEDURE [dbo].[displayInvoiceTable]
    @InvoiceId INT
AS
BEGIN
    SET NOCOUNT ON;

    SELECT 
        Products.ProductName, 
        Products.SellPrice, 
        ii.Quantity
    FROM 
        Products
    JOIN 
        Imports ON Products.ProductId = Imports.ProductId
    JOIN 
        Invoice_Items AS ii ON Imports.ImportId = ii.ImportId
    WHERE 
        ii.InvoiceId = @InvoiceId;
END

