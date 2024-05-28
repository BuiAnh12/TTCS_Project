CREATE PROCEDURE EditCustomer
    @CustomerId INT,
    @CustomerName NVARCHAR(100),
    @Email NVARCHAR(100),
    @Address NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        UPDATE Customers
        SET 
            CustomerName = @CustomerName,
            Email = @Email,
            Address = @Address
        WHERE 
            CustomerId = @CustomerId;
    END TRY
    BEGIN CATCH
        -- Xử lý ngoại lệ (ví dụ: in ra thông báo lỗi)
        PRINT ERROR_MESSAGE();
    END CATCH
END


