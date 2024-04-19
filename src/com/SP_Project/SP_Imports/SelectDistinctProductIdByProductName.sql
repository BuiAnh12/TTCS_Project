CREATE PROCEDURE SelectDistinctProductIdByProductName
    @ProductName NVARCHAR(255)
AS
BEGIN
	SET NOCOUNT ON
    SELECT DISTINCT p.ProductId 
    FROM Products p 
    JOIN Imports i ON p.ProductId = i.ProductId
    WHERE p.ProductName = @ProductName;
END;
