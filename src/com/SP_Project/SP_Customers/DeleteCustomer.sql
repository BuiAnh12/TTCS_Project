CREATE PROCEDURE DeleteCustomer
    @CustomerId INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        DELETE FROM Customers
        WHERE CustomerId = @CustomerId;
    END TRY
    BEGIN CATCH
        -- Xử lý ngoại lệ (ví dụ: in ra thông báo lỗi)
        PRINT ERROR_MESSAGE();
    END CATCH
END


