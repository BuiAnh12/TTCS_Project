CREATE PROCEDURE SelectProductName
AS
BEGIN
	SET NOCOUNT ON 
    SELECT p.ProductName
    FROM Products p
    JOIN Imports i ON p.ProductId = i.ProductId;
END;
