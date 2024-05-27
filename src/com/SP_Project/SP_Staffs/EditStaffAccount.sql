CREATE PROCEDURE EditAccount
    @StaffId INT,
    @Username NVARCHAR(100),
    @Password NVARCHAR(100),
    @AccountPrevilege INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        UPDATE Staffs
        SET 
            Username = @Username,
            Password = @Password,
            AccountPrevilege = @AccountPrevilege
        WHERE 
            StaffId = @StaffId;
    END TRY
    BEGIN CATCH
        -- Xử lý ngoại lệ (ví dụ: in ra thông báo lỗi)
        PRINT ERROR_MESSAGE();
    END CATCH
END


