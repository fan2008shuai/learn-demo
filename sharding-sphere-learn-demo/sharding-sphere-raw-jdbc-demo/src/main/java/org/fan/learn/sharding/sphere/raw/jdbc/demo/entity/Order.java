package org.fan.learn.sharding.sphere.raw.jdbc.demo.entity;


import java.util.Date;

/**
 * @author author
 * @date 2022/12/2
 */
public class Order {
    private long orderId;
    private int userId;
    private long addressId;
    private String status;
    private Date createTime = new Date();
    private Date updateTime = new Date();

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long orderId;
        private int userId;
        private long addressId;
        private String status;
        private Date createTime;
        private Date updateTime;

        public Builder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder addressId(long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder createTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder updateTIme(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setAddressId(addressId);
            order.setStatus(status);
            order.setCreateTime(createTime == null ? new Date() : createTime);
            order.setUpdateTime(updateTime == null ? new Date() : updateTime);
            return order;
        }

    }


    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", addressId=" + addressId +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
