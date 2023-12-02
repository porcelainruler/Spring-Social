package com.shubham.project.spring_network.dto.response;

public class ModeratorDTO {

    private long id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String address;

    private boolean enabled;

    public ModeratorDTO () {

    }



    public static class ModeratorDTOBuilder {
        private long id;
        private String name;
        private String username;
        private String email;
        private String phone;
        private String address;
        private boolean enabled;

        public ModeratorDTOBuilder () {

        }

        public ModeratorDTOBuilder (long id, String name, String username, String email, String phone, String address, boolean enabled) {
            this.id = id;
            this.name = name;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.enabled = enabled;
        }

        public ModeratorDTOBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public ModeratorDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ModeratorDTOBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public ModeratorDTOBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ModeratorDTOBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ModeratorDTOBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public ModeratorDTOBuilder setEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public ModeratorDTO build () {
            return new ModeratorDTO(this);
        }
    }

    public ModeratorDTO(ModeratorDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.username = builder.username;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.enabled = builder.enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
