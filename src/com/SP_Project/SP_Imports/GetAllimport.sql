//get all import by Status and searchkey
Create procedure GetProductByStatusAndSearchKey
	@status int ,
	@key nvarchar(100)
as 
	begin
	set nocount on ;
		if @status =1 
			begin 
				SELECT * FROM Products 
				JOIN Imports ON Products.ProductId = Imports.ProductId 
				WHERE ProductName LIKE CONCAT('%', @key, '%') 
				ORDER BY ProductName;
			end 
		else if @status=2 
			begin 
				SELECT * FROM Products 
				JOIN Imports ON Products.ProductId = Imports.ProductId 
				WHERE ProductName LIKE CONCAT('%', @key, '%') 
				ORDER BY ImportQuantity;
			end
		else if @status=3 
			begin
				 SELECT * FROM Products 
				JOIN Imports ON Products.ProductId = Imports.ProductId 
				WHERE ProductName LIKE CONCAT('%', @key, '%') 
				ORDER BY AvailableQuantity;
			end
end ;