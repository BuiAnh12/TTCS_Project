CREATE PROCEDURE findListProduct
    @SearchKeyword NVARCHAR(255)
AS
BEGIN
	SET NOCOUNT ON
    SELECT * 
    FROM Products 
    WHERE ProductName LIKE '%' + @SearchKeyword + '%';
END;
