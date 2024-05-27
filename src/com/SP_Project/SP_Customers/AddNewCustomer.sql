CREATE PROCEDURE AddCustomer
    @CustomerName NVARCHAR(100),
    @Email NVARCHAR(100),
    @Address NVARCHAR(255)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        INSERT INTO Customers (CustomerName, Email, Address)
        VALUES (@CustomerName, @Email, @Address);
    END TRY
    BEGIN CATCH
        -- Xử lý ngoại lệ (ví dụ: in ra thông báo lỗi)
        PRINT ERROR_MESSAGE();
    END CATCH
END
