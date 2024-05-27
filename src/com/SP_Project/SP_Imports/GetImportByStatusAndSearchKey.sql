USE [DOAN_PHANBON]
GO

CREATE PROCEDURE [dbo].[GetImportByStatusAndSearchKey]
	@status INT,
	@key NVARCHAR(100)
AS
BEGIN
	SET NOCOUNT ON;

	IF @status = 1
	BEGIN
		SELECT * 
		FROM Products 
		JOIN Imports ON Products.ProductId = Imports.ProductId 
		WHERE ProductName LIKE CONCAT('%', @key, '%') 
		ORDER BY ProductName;
	END
	ELSE IF @status = 2
	BEGIN
		SELECT * 
		FROM Products 
		JOIN Imports ON Products.ProductId = Imports.ProductId 
		WHERE ProductName LIKE CONCAT('%', @key, '%') 
		ORDER BY ImportQuantity;
	END
	ELSE IF @status = 3
	BEGIN
		SELECT * 
		FROM Products 
		JOIN Imports ON Products.ProductId = Imports.ProductId 
		WHERE ProductName LIKE CONCAT('%', @key, '%') 
		ORDER BY AvailableQuantity;
	END
END
GO
