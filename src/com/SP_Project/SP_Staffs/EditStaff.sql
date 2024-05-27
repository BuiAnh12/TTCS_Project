CREATE PROCEDURE EditStaff
    @StaffId INT,
    @Name NVARCHAR(100),
    @Age INT,
    @Email NVARCHAR(100),
    @Address NVARCHAR(255),
    @AccountPrevilege INT,
    @Username NVARCHAR(100),
    @Password NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        UPDATE Staffs
        SET 
            Name = @Name,
            Age = @Age,
            Email = @Email,
            Address = @Address,
            AccountPrevilege = @AccountPrevilege,
            Username = @Username,
            Password = @Password
        WHERE 
            StaffId = @StaffId;
    END TRY
    BEGIN CATCH
        -- Xử lý ngoại lệ (ví dụ: in ra thông báo lỗi)
        PRINT ERROR_MESSAGE();
    END CATCH
END
