CREATE PROCEDURE deleteProduct
    @ProductId INT
AS
BEGIN
	SET NOCOUNT ON
    DELETE FROM Products
    WHERE ProductId = @ProductId;
END;
