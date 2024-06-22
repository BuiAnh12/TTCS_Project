CREATE PROCEDURE [dbo].[EditStaff]
    @StaffId INT,
    @Name NVARCHAR(100),
    @Age INT,
    @Email NVARCHAR(100),
    @Address NVARCHAR(255),
    @AccountPrevilege INT,
    @Username NVARCHAR(100),
    @Password NVARCHAR(100),
	@Result INT OUTPUT -- Thêm một tham số output
AS
BEGIN
    SET NOCOUNT ON;
	-- Bắt đầu giao dịch
    BEGIN TRANSACTION;

    -- Kiểm tra xem email đã tồn tại hay chưa
    IF EXISTS (SELECT 1 FROM Staffs WHERE Email = @Email AND StaffID != @StaffID)
    BEGIN
        -- Rollback transaction nếu email đã tồn tại và trả về kết quả -1
        SET @Result = -1;
        ROLLBACK TRANSACTION;
        RETURN;
    END

    -- Kiểm tra xem username đã tồn tại hay chưa
    IF EXISTS (SELECT 1 FROM Staffs WHERE Username = @Username AND StaffID != @StaffID)
    BEGIN
        -- Rollback transaction nếu username đã tồn tại và trả về kết quả -2
        SET @Result = -2;
        ROLLBACK TRANSACTION;
        RETURN;
    END

    
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
    COMMIT TRANSACTION;
    SET @Result = 1;
    RETURN;
END