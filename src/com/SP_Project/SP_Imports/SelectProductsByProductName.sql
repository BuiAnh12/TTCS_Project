CREATE PROCEDURE SelectProductsByProductName
    @ProductNameFilter NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON
    SELECT * 
    FROM Products 
    JOIN Imports ON Products.ProductId = Imports.ProductId 
    WHERE ProductName LIKE '%' + @ProductNameFilter + '%';
END;